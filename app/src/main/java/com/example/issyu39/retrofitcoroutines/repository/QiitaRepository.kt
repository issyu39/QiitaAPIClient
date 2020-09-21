package com.example.issyu39.retrofitcoroutines.repository

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.network.QiitaApiClient

class QiitaRepository {
    private val api = QiitaApiClient.create()

    /**
     * Qiitaの記事を取得する
     */
    suspend fun getArticleList(page: Int, per_page: Int): List<Article> {
        return api.getArticleList(page, per_page)
    }

}