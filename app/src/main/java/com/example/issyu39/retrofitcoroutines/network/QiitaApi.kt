package com.example.issyu39.retrofitcoroutines.network

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface QiitaApi {
    @GET("items/?page=1&per_page=100")
    fun getArticle(
    ): Deferred<List<Article>>

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: String
    ): Deferred<User>
}