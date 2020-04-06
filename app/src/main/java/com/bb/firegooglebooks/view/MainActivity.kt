package com.bb.firegooglebooks.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bb.firegooglebooks.R
import com.bb.firegooglebooks.adapter.GoogleBookAdapter
import com.bb.firegooglebooks.adapter.GoogleBookAdapter.FavoriteBookInterface
import com.bb.firegooglebooks.model.FavoriteBooks
import com.bb.firegooglebooks.model.GoogleBooks
import com.bb.firegooglebooks.viewmodel.GoogleBookViewModel
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity(), FavoriteBookInterface {
    private lateinit var viewModel: GoogleBookViewModel
    private val compositeDisposable = CompositeDisposable()
    private lateinit var bookTitleEditText: EditText
    private lateinit var searchedBookTitleList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(GoogleBookViewModel::class.java)
        bookTitleEditText = findViewById(R.id.book_title_editText)
        searchedBookTitleList = findViewById(R.id.searched_book_title_list)
    }

    fun searchBookTitle(view: View) {
        Log.d("TAG_X", "This button is click")
        val bookTitle = bookTitleEditText.text.toString()
        Log.d("TAG_X", "Search Book: $bookTitle")
        compositeDisposable.add(viewModel.getGoogleBookListRx(bookTitle).subscribe({ googleBooks: GoogleBooks -> displayGoogleBookListRx(googleBooks) }
        ) { throwable: Throwable -> Log.d("TAG_XX", "Error: " + throwable.localizedMessage) })
        bookTitleEditText.setText("")
    }

    private fun displayGoogleBookListRx(googleBooks: GoogleBooks) {
        for (i in googleBooks.items.indices) {
            Log.d("TAG_XX", "RxJava: " + googleBooks.items[i].volumeInfo.title)
            searchedBookTitleList.layoutManager = LinearLayoutManager(this)
            searchedBookTitleList.adapter = GoogleBookAdapter(googleBooks, this)
        }
    }

    fun goToFavorites(view: View) {
        val intent = Intent(this, ViewFavoriteBooks::class.java)
        startActivity(intent)
    }

    override fun addToFavorite(favoriteBooks: FavoriteBooks) {
        Log.d("TAG_XX", "Favorite")
        Log.d("TAG_XX", "Favorite Book Title: " + favoriteBooks.favoriteBookTitle)
        val favoriteBook = favoriteBooks.favoriteBookTitle
        val newFavoriteBook = FavoriteBooks(favoriteBook)
        viewModel.addToFavorite(newFavoriteBook)
    }
}