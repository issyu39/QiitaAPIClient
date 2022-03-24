package com.example.issyu39.retrofitcoroutines.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.issyu39.retrofitcoroutines.R
import com.example.issyu39.retrofitcoroutines.databinding.ActivityMainBinding
import com.example.issyu39.retrofitcoroutines.ext.viewBinding
import com.example.issyu39.retrofitcoroutines.ui.model.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.articleList.collect { uiState ->
                    when (uiState) {
                        is State.Loading -> {
                            binding.progressbar.visibility = View.VISIBLE
                        }
                        is State.Success -> {
                            binding.progressbar.visibility = View.INVISIBLE
                            adapter.updateList(uiState.data)
                        }
                        is State.Failure -> {
                            binding.progressbar.visibility = View.INVISIBLE
                            // FIXME: エラー時の表示は検討する
                            Toast.makeText(this@MainActivity, "通信エラーが発生しました。", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
