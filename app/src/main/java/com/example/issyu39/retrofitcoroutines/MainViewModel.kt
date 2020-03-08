package com.example.issyu39.retrofitcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.network.ApiService

class MainViewModel : ViewModel() {
    private val api = ApiService.get()

    val articleList: LiveData<List<Article>> = liveData(viewModelScope.coroutineContext) {
        runCatching {
            api.getArticle()
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