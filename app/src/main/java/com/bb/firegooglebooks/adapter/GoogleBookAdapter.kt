package com.bb.firegooglebooks.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bb.firegooglebooks.R
import com.bb.firegooglebooks.adapter.GoogleBookAdapter.GoogleBookViewHolder
import com.bb.firegooglebooks.model.FavoriteBooks
import com.bb.firegooglebooks.model.GoogleBooks

class GoogleBookAdapter(private val googleBooks: GoogleBooks, private val favoriteBookInterface: FavoriteBookInterface)
    : RecyclerView.Adapter<GoogleBookViewHolder>() {

    interface FavoriteBookInterface {
        fun addToFavorite(favoriteBooks: FavoriteBooks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleBookViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.searched_book_title_list_layout, parent, false)
        return GoogleBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoogleBookViewHolder, position: Int) {
        val slide = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.right_to_left)
        holder.itemView.startAnimation(slide)
        holder.searchedBookTitle.text = googleBooks.items[position].volumeInfo.title
        holder.addToFavorite.setOnClickListener {
            Log.d("TAG_X", "Favorite is clicked")
            val favoriteBookTitle = holder.searchedBookTitle.text.toString()
            val favoriteBooks = FavoriteBooks(favoriteBookTitle)
            favoriteBookInterface.addToFavorite(favoriteBooks)
            //Log.d("TAG_X", "Favorite: " + holder.searchedBookTitle.getText().toString());
        }
    }

    override fun getItemCount(): Int {
        return googleBooks.items.size
    }

    inner class GoogleBookViewHolder(itemView: View) : ViewHolder(itemView) {
        var searchedBookTitle: TextView = itemView.findViewById(R.id.searched_book_title_textview)
        var addToFavorite: Button = itemView.findViewById(R.id.add_to_favorite_button)

    }

}