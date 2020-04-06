package com.bb.firegooglebooks.model;

public class FavoriteBooks {

    private String favoriteBookTitle;

    public FavoriteBooks() {
    }

    public FavoriteBooks(String favoriteBookTitle) {
        this.favoriteBookTitle = favoriteBookTitle;
    }

    public String getFavoriteBookTitle() {
        return favoriteBookTitle;
    }

    public void setFavoriteBookTitle(String favoriteBookTitle) {
        this.favoriteBookTitle = favoriteBookTitle;
    }
}
