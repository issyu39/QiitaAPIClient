package com.example.issyu39.retrofitcoroutines.network

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QiitaService {
    // 記事の一覧を作成日時の降順で返す
    @GET("items")
    suspend fun getArticleList(
        @Query("page") page: String, // ページ番号 (1から100まで)
        @Query("per_page") perPage: String, // 1ページあたりに含まれる要素数 (1から100まで)
        @Query("query") query: String? = null // 検索クエリ 例:"Android+Room"
    ): List<Article>

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): User
}