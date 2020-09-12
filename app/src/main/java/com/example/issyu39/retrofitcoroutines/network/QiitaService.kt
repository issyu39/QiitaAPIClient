package com.example.issyu39.retrofitcoroutines.network

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface QiitaService {
    @GET("items/?page=1&per_page=50")
    suspend fun getArticle(): List<Article>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: String): User
}