package com.example.issyu39.retrofitcoroutines.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String?,
    val user: User?
)