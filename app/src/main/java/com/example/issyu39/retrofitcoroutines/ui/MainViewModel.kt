package com.example.issyu39.retrofitcoroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.repository.QiitaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val qiitaRepository = QiitaRepository()

    private val _articleList = MutableStateFlow<List<Article>>(emptyList())
    val articleList: StateFlow<List<Article>> = _articleList

    fun getArticleList(page: Int, query: String? = null) {
        viewModelScope.launch {
            runCatching {
                qiitaRepository.getArticleList(page, query)
                    .collect {
                        _articleList.value = it
                    }
            }
        }
    }
}