package com.example.issyu39.retrofitcoroutines.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.issyu39.retrofitcoroutines.R
import com.example.issyu39.retrofitcoroutines.databinding.LayoutItemListBinding
import com.example.issyu39.retrofitcoroutines.model.Article

class ArticleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val _articleList = mutableListOf<Article>()
    private val articleList: List<Article> = _articleList

    override fun getItemCount(): Int = articleList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> holder.bind(articleList[position])
            else -> throw IllegalStateException("$holder is not defined view holder type.")
        }
    }

    fun updateList(list: List<Article>) {
        _articleList.clear()
        _articleList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemViewHolder private constructor(
        private val binding: LayoutItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.listItemTitle.text = article.title
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return ItemViewHolder(
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.layout_item_list,
                        parent,
                        false
                    )
                )
            }
        }
    }
}
