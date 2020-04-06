package com.bb.firegooglebooks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bb.firegooglebooks.R;
import com.bb.firegooglebooks.adapter.GoogleBookAdapter;
import com.bb.firegooglebooks.model.FavoriteBooks;
import com.bb.firegooglebooks.model.GoogleBooks;
import com.bb.firegooglebooks.viewmodel.GoogleBookViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity implements  GoogleBookAdapter.FavoriteBookInterface{

    private GoogleBookViewModel viewModel;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText bookTitleEditText;

    RecyclerView searchedBookTitleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(GoogleBookViewModel.class);

        bookTitleEditText = findViewById(R.id.book_title_editText);
        searchedBookTitleList = findViewById(R.id.searched_book_title_list);

    }

    public void searchBookTitle(View view){
        Log.d("TAG_X", "This button is click");
        String bookTitle = bookTitleEditText.getText().toString();
        Log.d("TAG_X", "Search Book: " + bookTitle);

        compositeDisposable.add(viewModel.getGoogleBookListRx(bookTitle).subscribe(googleBooks -> {
            displayGoogleBookListRx(googleBooks);
        }, throwable -> {Log.d("TAG_XX", "Error: " + throwable.getLocalizedMessage());}
        ));

        bookTitleEditText.setText("");
    }

    private void displayGoogleBookListRx(GoogleBooks googleBooks){
        for(int i = 0; i < googleBooks.getItems().size(); i++){
            Log.d("TAG_XX", "RxJava: " + googleBooks.getItems().get(i).getVolumeInfo().getTitle());
            searchedBookTitleList.setLayoutManager(new LinearLayoutManager(this));
            searchedBookTitleList.setAdapter(new GoogleBookAdapter(googleBooks, this));
        }
    }

    public void goToFavorites(View view){
        Intent intent = new Intent(this, ViewFavoriteBooks.class);
        startActivity(intent);
    }

    @Override
    public void addToFavorite(FavoriteBooks favoriteBooks) {
        Log.d("TAG_XX", "Favorite");
        Log.d("TAG_XX", "Favorite Book Title: " + favoriteBooks.getFavoriteBookTitle());
        String favoriteBook = favoriteBooks.getFavoriteBookTitle();

        FavoriteBooks newFavoriteBook = new FavoriteBooks(favoriteBook);
        viewModel.addToFavorite(newFavoriteBook);

    }

}
