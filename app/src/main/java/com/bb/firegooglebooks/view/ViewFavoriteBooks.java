package com.bb.firegooglebooks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bb.firegooglebooks.R;
import com.bb.firegooglebooks.adapter.FavoriteBookAdapter;
import com.bb.firegooglebooks.model.FavoriteBooks;
import com.bb.firegooglebooks.viewmodel.GoogleBookViewModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ViewFavoriteBooks extends AppCompatActivity {

    GoogleBookViewModel viewModel;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    RecyclerView favoriteBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorite_books);

        favoriteBookList = findViewById(R.id.favorite_book_list);

        viewModel = ViewModelProviders.of(this).get(GoogleBookViewModel.class);

        compositeDisposable.add(viewModel.getFavoriteBooksList().subscribe(favoriteBooksList -> {
            displayFavoriteBooks(favoriteBooksList);
        }));

    }

    private void displayFavoriteBooks(List<FavoriteBooks> favoriteBooksList){
        for(int i = 0; i < favoriteBooksList.size(); i++){
            Log.d("TAG_XXX", "Favorite RxJava: " + favoriteBooksList.get(i).getFavoriteBookTitle());
            favoriteBookList.setLayoutManager(new LinearLayoutManager(this));
            favoriteBookList.setAdapter(new FavoriteBookAdapter(favoriteBooksList));
        }
    }

    public void returnToHome(View view){
        finish();
    }
}
