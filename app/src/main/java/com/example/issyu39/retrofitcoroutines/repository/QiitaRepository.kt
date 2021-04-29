package com.example.issyu39.retrofitcoroutines.repository

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.network.QiitaApiClient

class QiitaRepository {
    private val api = QiitaApiClient.create()

    /**
     * 記事一覧を取得する
     */
    suspend fun getArticleList(page: Int, query: String?): List<Article> {
        return api.getArticleList(page.toString(), PER_PAGE, query)
    }

    companion object {
        private const val PER_PAGE = "50"
    }

}