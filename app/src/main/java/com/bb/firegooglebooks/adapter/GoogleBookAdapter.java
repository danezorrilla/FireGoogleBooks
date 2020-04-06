package com.bb.firegooglebooks.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.firegooglebooks.R;
import com.bb.firegooglebooks.model.FavoriteBooks;
import com.bb.firegooglebooks.model.GoogleBooks;

public class GoogleBookAdapter extends RecyclerView.Adapter<GoogleBookAdapter.GoogleBookViewHolder> {

    public interface FavoriteBookInterface{
        void addToFavorite(FavoriteBooks favoriteBooks);
    }

    private GoogleBooks googleBooks;
    private FavoriteBookInterface favoriteBookInterface;

    public GoogleBookAdapter(GoogleBooks googleBooks, FavoriteBookInterface favoriteBookInterface) {
        this.googleBooks = googleBooks;
        this.favoriteBookInterface = favoriteBookInterface;
    }

    @NonNull
    @Override
    public GoogleBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searched_book_title_list_layout, parent, false);
        return new GoogleBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoogleBookViewHolder holder, int position) {
        Animation slide = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.right_to_left);
        holder.itemView.startAnimation(slide);

        holder.searchedBookTitle.setText(googleBooks.getItems().get(position).getVolumeInfo().getTitle());
        holder.addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG_X", "Favorite is clicked");
                String favoriteBookTitle = holder.searchedBookTitle.getText().toString();
                FavoriteBooks favoriteBooks = new FavoriteBooks(favoriteBookTitle);
                favoriteBookInterface.addToFavorite(favoriteBooks);
                //Log.d("TAG_X", "Favorite: " + holder.searchedBookTitle.getText().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return googleBooks.getItems().size();
    }

    public class GoogleBookViewHolder extends RecyclerView.ViewHolder {

        TextView searchedBookTitle;

        Button addToFavorite;

        public GoogleBookViewHolder(@NonNull View itemView) {
            super(itemView);

            searchedBookTitle = itemView.findViewById(R.id.searched_book_title_textview);
            addToFavorite = itemView.findViewById(R.id.add_to_favorite_button);
        }
    }
}
