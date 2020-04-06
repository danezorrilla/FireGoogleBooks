package com.bb.firegooglebooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bb.firegooglebooks.model.FavoriteBooks
import com.bb.firegooglebooks.model.GoogleBooks
import com.bb.firegooglebooks.network.GoogleBookListRetrofit
import com.google.firebase.database.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*

class GoogleBookViewModel(application: Application) : AndroidViewModel(application) {
    private val googleBookListRetrofit: GoogleBookListRetrofit
    private val databaseReference: DatabaseReference
    private val favoriteBooksObservable = PublishSubject.create<List<FavoriteBooks>>()
    var favoriteBooksList: MutableList<FavoriteBooks> = ArrayList()
    fun getGoogleBookListRx(bookTitle: String?): Observable<GoogleBooks> {
        return googleBookListRetrofit
                .getGoogleBookList(bookTitle!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun getFavoriteBooksList(): Observable<List<FavoriteBooks>> {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val currentBook = ds.getValue(FavoriteBooks::class.java)!!
                    favoriteBooksList.add(currentBook)
                }
                favoriteBooksObservable.onNext(favoriteBooksList)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return favoriteBooksObservable
    }

    fun addToFavorite(favoriteBooks: FavoriteBooks?) {
        val databaseKey = databaseReference.push().key
        if (databaseKey != null) databaseReference.child(databaseKey).setValue(favoriteBooks)
    }

    init {
        googleBookListRetrofit = GoogleBookListRetrofit()
        databaseReference = FirebaseDatabase.getInstance().reference.child("books")
    }
}