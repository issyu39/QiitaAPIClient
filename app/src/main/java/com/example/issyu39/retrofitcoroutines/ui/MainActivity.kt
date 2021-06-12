package com.example.issyu39.retrofitcoroutines.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.issyu39.retrofitcoroutines.R
import com.example.issyu39.retrofitcoroutines.databinding.ActivityMainBinding
import com.example.issyu39.retrofitcoroutines.ui.model.State
import com.example.issyu39.retrofitcoroutines.ext.viewBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private val adapter = ArticleListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getArticleList(1, "Android")
        binding.articleListView.adapter = adapter
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.articleList.collect { uiState ->
                when (uiState)  {
                    is State.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is State.Success -> {
                        binding.progressbar.visibility = View.INVISIBLE
                        adapter.updateList(uiState.data)
                    }
                    is State.Failure -> {
                        binding.progressbar.visibility = View.INVISIBLE
                        // TODO: エラー処理
                    }
                }
            }
        }
    }
}
