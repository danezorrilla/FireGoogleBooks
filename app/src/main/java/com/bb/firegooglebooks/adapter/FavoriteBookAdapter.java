package com.bb.firegooglebooks.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.firegooglebooks.R;
import com.bb.firegooglebooks.model.FavoriteBooks;

import java.util.List;

public class FavoriteBookAdapter extends RecyclerView.Adapter<FavoriteBookAdapter.FavoriteBookViewHolder> {

    private List<FavoriteBooks> favoriteBooksList;

    public FavoriteBookAdapter(List<FavoriteBooks> favoriteBooksList) {
        this.favoriteBooksList = favoriteBooksList;
    }

    @NonNull
    @Override
    public FavoriteBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_book_title_list_layout, parent, false);
        return new FavoriteBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteBookViewHolder holder, int position) {
        Animation slide = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.slide_in);
        holder.itemView.startAnimation(slide);

        holder.favoriteBookTitle.setText(favoriteBooksList.get(position).getFavoriteBookTitle());
    }

    @Override
    public int getItemCount() {
        return favoriteBooksList.size();
    }

    public class FavoriteBookViewHolder extends RecyclerView.ViewHolder {

        TextView favoriteBookTitle;

        public FavoriteBookViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteBookTitle = itemView.findViewById(R.id.favorite_book_title);
        }
    }
}
