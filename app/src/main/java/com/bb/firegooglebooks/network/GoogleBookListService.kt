package com.bb.firegooglebooks.network

import com.bb.firegooglebooks.model.GoogleBooks
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBookListService {
    @GET("/books/v1/volumes")
    fun getGoogleBookList(@Query("q") query: String?, @Query("key") key: String): Observable<GoogleBooks>
}