package com.bb.firegooglebooks.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bb.firegooglebooks.R
import com.bb.firegooglebooks.adapter.FavoriteBookAdapter
import com.bb.firegooglebooks.model.FavoriteBooks
import com.bb.firegooglebooks.viewmodel.GoogleBookViewModel
import io.reactivex.disposables.CompositeDisposable

class ViewFavoriteBooks : AppCompatActivity() {
    private lateinit var viewModel: GoogleBookViewModel
    var compositeDisposable = CompositeDisposable()
    private lateinit var favoriteBookList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_favorite_books)
        favoriteBookList = findViewById(R.id.favorite_book_list)
        viewModel = ViewModelProviders.of(this).get(GoogleBookViewModel::class.java)
        compositeDisposable.add(viewModel.getFavoriteBooksList().subscribe(this::displayFavoriteBooks))
    }

    private fun displayFavoriteBooks(favoriteBooksList: List<FavoriteBooks>) {
        for (i in favoriteBooksList.indices) {
            Log.d("TAG_XXX", "Favorite RxJava: " + favoriteBooksList[i].favoriteBookTitle)
            favoriteBookList.layoutManager = LinearLayoutManager(this)
            favoriteBookList.adapter = FavoriteBookAdapter(favoriteBooksList)
        }
    }

    fun returnToHome(view: View) {
        finish()
    }
}