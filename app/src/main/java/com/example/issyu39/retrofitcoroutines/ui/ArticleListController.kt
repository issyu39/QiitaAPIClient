package com.example.issyu39.retrofitcoroutines.ui

import com.airbnb.epoxy.TypedEpoxyController
import com.example.issyu39.retrofitcoroutines.databinding.LayoutItemListBinding
import com.example.issyu39.retrofitcoroutines.layoutItemList
import com.example.issyu39.retrofitcoroutines.model.Article

class ArticleListController() : TypedEpoxyController<List<Article>>() {
    override fun buildModels(dataList: List<Article>) {
        dataList.forEach { data ->
            layoutItemList {
                id(data.title)
                onBind { _, view, _ ->
                    val binding = view.dataBinding as LayoutItemListBinding
                    binding.listItemTitle.text = data.title
                }
            }
        }
    }
}
