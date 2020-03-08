package com.example.issyu39.retrofitcoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.issyu39.retrofitcoroutines.network.QiitaService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Retrofit.Builder()
            .baseUrl("https://qiita.com/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(QiitaService::class.java)

        GlobalScope.launch {
            service.getArticle().await().forEach { println("$it")}
        }
    }
}
