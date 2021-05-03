package com.example.issyu39.retrofitcoroutines.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.issyu39.retrofitcoroutines.R
import com.example.issyu39.retrofitcoroutines.databinding.ActivityMainBinding
import com.example.issyu39.retrofitcoroutines.ext.viewBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private val articleListController = ArticleListController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.articleListView.adapter = articleListController.adapter
        mainViewModel.getArticleList(1, "Android")
        lifecycleScope.launchWhenStarted {
            mainViewModel.articleList.collect {
                articleListController.setData(it)
            }
        }
    }
}
