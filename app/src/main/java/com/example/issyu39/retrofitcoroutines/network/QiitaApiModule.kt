package com.example.issyu39.retrofitcoroutines.network

import com.example.issyu39.retrofitcoroutines.repository.QiitaRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object QiitaApiModule {
    private const val BASE_URL = "https://qiita.com/api/v2/"
    private val contentType = MediaType.get("application/json")

    private val json = Json { ignoreUnknownKeys = true }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideQiitaService(retrofit: Retrofit): QiitaService =
        retrofit.create(QiitaService::class.java)

    @Singleton
    @Provides
    fun providesQiitaRepository(qiitaService: QiitaService) = QiitaRepository(qiitaService)
}