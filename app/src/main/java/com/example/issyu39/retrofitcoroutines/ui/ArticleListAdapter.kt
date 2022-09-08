package com.example.issyu39.retrofitcoroutines.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.issyu39.retrofitcoroutines.R
import com.example.issyu39.retrofitcoroutines.databinding.LayoutArticleBinding
import com.example.issyu39.retrofitcoroutines.model.Article

class ArticleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _articleList = mutableListOf<Article>()
    private val articleList: List<Article> = _articleList

    override fun getItemCount(): Int = articleList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ArticleViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> holder.bind(articleList[position])
            else -> throw IllegalStateException("$holder is not defined view holder type.")
        }
    }

    fun updateList(list: List<Article>) {
        _articleList.clear()
        _articleList.addAll(list)
        notifyDataSetChanged()
    }

    class ArticleViewHolder private constructor(
        private val binding: LayoutArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.articleTitle.text = article.title
            binding.userOrganization.text = article.user.organization
            binding.userId.text = article.user.id
        }

        companion object {
            fun create(parent: ViewGroup): ArticleViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return ArticleViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.layout_article,
                        parent,
                        false
                    )
                )
            }
        }
    }
}
