package com.example.issyu39.retrofitcoroutines.repository

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.network.QiitaApiClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QiitaRepository {
    private val api = QiitaApiClient.create()

    /**
     * 記事一覧を取得する
     */
    suspend fun getArticleList(page: Int, query: String?): Flow<List<Article>> = flow {
        emit(api.getArticleList(page.toString(), PER_PAGE, query))
    }

    companion object {
        private const val PER_PAGE = "50"
    }

}