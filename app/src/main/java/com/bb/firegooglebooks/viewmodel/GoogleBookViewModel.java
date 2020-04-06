package com.bb.firegooglebooks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bb.firegooglebooks.model.FavoriteBooks;
import com.bb.firegooglebooks.model.GoogleBooks;
import com.bb.firegooglebooks.network.GoogleBookListRetrofit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class GoogleBookViewModel extends AndroidViewModel {

    private GoogleBookListRetrofit googleBookListRetrofit;

    private DatabaseReference databaseReference;

    private PublishSubject<List<FavoriteBooks>> favoriteBooksObservable = PublishSubject.create();

    List<FavoriteBooks> favoriteBooksList = new ArrayList<>();


    public GoogleBookViewModel(@NonNull Application application) {
        super(application);
        googleBookListRetrofit = new GoogleBookListRetrofit();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("books");

    }

    public Observable<GoogleBooks> getGoogleBookListRx(String bookTitle){
        return googleBookListRetrofit
                .getGoogleBookList(bookTitle)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<List<FavoriteBooks>> getFavoriteBooksList(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    FavoriteBooks currentBook = ds.getValue(FavoriteBooks.class);
                    favoriteBooksList.add(currentBook);
                }
                favoriteBooksObservable.onNext(favoriteBooksList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return favoriteBooksObservable;
    }

    public void addToFavorite(FavoriteBooks favoriteBooks){
        String databaseKey = databaseReference.push().getKey();
        if(databaseKey != null)
            databaseReference.child(databaseKey).setValue(favoriteBooks);
    }
}
