package com.example.issyu39.retrofitcoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GitHubService::class.java)

        GlobalScope.launch {
            val repositories = service.retrieveRepositories("issyu39").await()
            repositories.forEach { println("TAG_ $it") }
            val users = service.retrieveUsers().await()
            users.forEach { println("TAG_ $it") }
        }
    }
}
