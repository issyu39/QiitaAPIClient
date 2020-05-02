package com.example.issyu39.retrofitcoroutines.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType
import retrofit2.Retrofit

class ApiService {
    companion object {
        val contentType = MediaType.get("application/json")
        val config = JsonConfiguration.Stable.copy(
            ignoreUnknownKeys = true,
            isLenient = true,
            serializeSpecialFloatingPointValues = true,
            useArrayPolymorphism = true
        )

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://qiita.com/api/v2/")
                .addConverterFactory(Json(config).asConverterFactory(contentType))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        fun get(): QiitaApi {
            return getRetrofit().create(QiitaApi::class.java)
        }
    }
}