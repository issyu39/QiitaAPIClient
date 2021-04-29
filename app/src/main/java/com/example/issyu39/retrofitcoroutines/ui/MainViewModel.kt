package com.example.issyu39.retrofitcoroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.repository.QiitaRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val qiitaRepository = QiitaRepository()

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>>
        get() = _articleList

    fun getArticleList(page: Int, query: String? = null) {
        viewModelScope.launch {
            runCatching {
                qiitaRepository.getArticleList(page, query)
            }.fold(
                onSuccess = {
                    _articleList.value = it
                },
                onFailure = {
                    // ...
                }
            )
        }
    }
}