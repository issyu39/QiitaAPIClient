package com.example.issyu39.retrofitcoroutines.data

import com.example.issyu39.retrofitcoroutines.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QiitaRepository(
    private val qiitaService: QiitaService
) {
    /**
     * 指定されたタグの記事一覧を取得する
     */
    suspend fun getArticleListByTag(tagName: String, page: Int): Flow<List<Article>> = flow {
        emit(qiitaService.getArticleListByTag(tagName, page.toString(), PER_PAGE))
    }

    companion object {
        private const val PER_PAGE = "50"
    }
}