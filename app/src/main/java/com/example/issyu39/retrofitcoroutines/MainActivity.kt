package com.example.issyu39.retrofitcoroutines

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.example.issyu39.retrofitcoroutines.databinding.ActivityMainBinding
import com.example.issyu39.retrofitcoroutines.ext.viewBinding
import com.example.issyu39.retrofitcoroutines.ui.ArticleListController

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding()
    private val articleListController = ArticleListController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.articleListView.adapter = articleListController.adapter
        mainViewModel.getArticleList(1, "Android")
        mainViewModel.articleList.observe(this) {
            articleListController.setData(it)
        }
    }
}
