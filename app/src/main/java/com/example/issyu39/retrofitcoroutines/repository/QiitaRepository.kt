package com.example.issyu39.retrofitcoroutines.repository

import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.network.QiitaApiModule
import com.example.issyu39.retrofitcoroutines.network.QiitaService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QiitaRepository(
    private val qiitaService: QiitaService
) {
    /**
     * 記事一覧を取得する
     */
    suspend fun getArticleList(page: Int, query: String?): Flow<List<Article>> = flow {
        emit(qiitaService.getArticleList(page.toString(), PER_PAGE, query))
    }

    companion object {
        private const val PER_PAGE = "50"
    }

}