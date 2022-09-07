package com.example.issyu39.retrofitcoroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.issyu39.retrofitcoroutines.model.Article
import com.example.issyu39.retrofitcoroutines.data.QiitaRepository
import com.example.issyu39.retrofitcoroutines.ui.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val qiitaRepository: QiitaRepository
) : ViewModel() {
    private val _articleList = MutableStateFlow<State<List<Article>>>(State.Success(emptyList()))
    val articleList: StateFlow<State<List<Article>>> = _articleList

    fun getArticleListByTag(tagName: String, page: Int) {
        _articleList.value = State.Loading()
        viewModelScope.launch {
            qiitaRepository.getArticleListByTag(tagName, page)
                .catch { exception ->
                    _articleList.value = State.Failure(exception)
                }
                .collect { articleList ->
                    _articleList.value = State.Success(articleList)
                }
        }
    }
}