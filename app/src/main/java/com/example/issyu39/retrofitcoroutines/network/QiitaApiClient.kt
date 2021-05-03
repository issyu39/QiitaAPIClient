package com.example.issyu39.retrofitcoroutines.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object QiitaApiClient {
    private const val BASE_URL = "https://qiita.com/api/v2/"
    private val contentType = MediaType.get("application/json")

    private val json = Json { ignoreUnknownKeys = true }

    fun create(): QiitaService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(QiitaService::class.java)
    }
}