package com.example.issyu39.retrofitcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.issyu39.retrofitcoroutines.databinding.ActivityMainBinding
import com.example.issyu39.retrofitcoroutines.ext.viewBinding
import com.example.issyu39.retrofitcoroutines.ui.ArticleListFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val tagList = listOf(
        "Android",
        "Flutter",
        "Kotlin",
        "React",
        "TypeScript"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.topPager.let { pager ->
            // 隣のpageをprefetchする
            pager.offscreenPageLimit = 1
            pager.adapter = object : FragmentStateAdapter(this) {
                override fun getItemCount(): Int = tagList.size
                override fun createFragment(position: Int) =
                    ArticleListFragment.newInstance(tagList[position])
            }
            TabLayoutMediator(binding.topTabLayout, pager) { tab, position ->
                tab.text = tagList[position]
            }.attach()
        }
    }
}
