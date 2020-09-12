package com.example.issyu39.retrofitcoroutines.repository

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.network.QiitaApiClient

class QiitaRepository {
    private val api = QiitaApiClient.create()

    /**
     * Qiitaの記事を取得する
     */
    suspend fun getArticle(): List<Article> {
        return api.getArticle()
    }
}