package com.example.issyu39.retrofitcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.repository.QiitaRepository

class MainViewModel : ViewModel() {
    private val qiitaRepository = QiitaRepository()

    val articleList: LiveData<List<Article>> = liveData(viewModelScope.coroutineContext) {
        runCatching {
            qiitaRepository.getArticle()
        }.fold(
            onSuccess = {
                emit(it)
            },
            onFailure = {
                // ...
            }
        )
    }
}