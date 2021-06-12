package com.example.issyu39.retrofitcoroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issyu39.retrofitcoroutines.ui.model.State
import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.repository.QiitaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val qiitaRepository = QiitaRepository()

    private val _articleList = MutableStateFlow<State<List<Article>>>(State.Success(emptyList()))
    val articleList: StateFlow<State<List<Article>>> = _articleList

    fun getArticleList(page: Int, query: String? = null) {
        _articleList.value = State.Loading()
        viewModelScope.launch {
            qiitaRepository.getArticleList(page, query)
                .collect { articleList ->
                    _articleList.value = State.Success(articleList)
                }
        }
    }
}