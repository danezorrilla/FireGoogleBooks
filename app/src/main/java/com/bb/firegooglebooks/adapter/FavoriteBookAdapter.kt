package com.bb.firegooglebooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bb.firegooglebooks.R
import com.bb.firegooglebooks.adapter.FavoriteBookAdapter.FavoriteBookViewHolder
import com.bb.firegooglebooks.model.FavoriteBooks

class FavoriteBookAdapter(private val favoriteBooksList: List<FavoriteBooks>) : RecyclerView.Adapter<FavoriteBookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBookViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_book_title_list_layout, parent, false)
        return FavoriteBookViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteBookViewHolder, position: Int) {
        val slide = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_in)
        holder.itemView.startAnimation(slide)
        holder.favoriteBookTitle.text = favoriteBooksList[position].favoriteBookTitle
    }

    override fun getItemCount(): Int {
        return favoriteBooksList.size
    }

    inner class FavoriteBookViewHolder(itemView: View) : ViewHolder(itemView) {
        var favoriteBookTitle: TextView = itemView.findViewById(R.id.favorite_book_title)

    }

}