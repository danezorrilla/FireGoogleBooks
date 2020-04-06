package com.bb.firegooglebooks.network

import com.bb.firegooglebooks.model.GoogleBooks
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GoogleBookListRetrofit {
    private val googleBookListService: GoogleBookListService

    private val retrofit: Retrofit
        private get() = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    private fun createService(retrofit: Retrofit): GoogleBookListService {
        return retrofit.create(GoogleBookListService::class.java)
    }

    fun getGoogleBookList(bookTitle: String): Observable<GoogleBooks> {
        return googleBookListService.getGoogleBookList(bookTitle, "AIzaSyDor7mTjlR8qbxOT5Kps7oxKhaC1ynWoX8")
    }

    init {
        googleBookListService = createService(retrofit)
    }
}