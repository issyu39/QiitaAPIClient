package com.example.issyu39.retrofitcoroutines.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.issyu39.retrofitcoroutines.R
import com.example.issyu39.retrofitcoroutines.databinding.FragmentArticleListBinding
import com.example.issyu39.retrofitcoroutines.ext.viewBinding
import com.example.issyu39.retrofitcoroutines.ui.model.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleListFragment : Fragment(R.layout.fragment_article_list) {
    private val articleListViewModel: ArticleListViewModel by viewModels()
    private val binding: FragmentArticleListBinding by viewBinding()
    private val adapter = ArticleListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.articleRecyclerView.adapter = adapter
        requireArguments().getString(TAG_NAME)?.let { tagName ->
            articleListViewModel.getArticleListByTag(tagName, 1)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                articleListViewModel.articleList.collect { uiState ->
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
                            Toast.makeText(context, "通信エラーが発生しました。", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG_NAME = "tagName"
        fun newInstance(tagName: String): ArticleListFragment =
            ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putString(TAG_NAME, tagName)
                }
            }
    }
}