package com.example.issyu39.retrofitcoroutines.network

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QiitaService {
    @GET("items")
    suspend fun getArticleList(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int): List<Article>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): User
}