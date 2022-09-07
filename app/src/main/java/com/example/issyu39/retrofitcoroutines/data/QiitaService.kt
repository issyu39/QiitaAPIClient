package com.example.issyu39.retrofitcoroutines.data

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface QiitaService {
    // Qiita API v2
    // https://qiita.com/api/v2/docs

    // 指定されたタグの記事一覧を降順で返す
    @GET("tags/{tag_id}/items")
    suspend fun getArticleListByTag(
        @Path("tag_id") tagId: String, // タグID 例:"Android"
        @Query("page") page: String, // ページ番号 (1から100まで)
        @Query("per_page") perPage: String, // 1ページあたりに含まれる要素数 (1から100まで)
    ): List<Article>

    @GET("users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): User
}