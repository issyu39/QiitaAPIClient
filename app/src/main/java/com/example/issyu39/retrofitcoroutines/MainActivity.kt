package com.example.issyu39.retrofitcoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.issyu39.retrofitcoroutines.network.ApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val api = ApiService.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            api.getArticle().await().forEach { println("$it")}
        }
    }
}
