package com.example.issyu39.retrofitcoroutines.model

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val id: String,
    val organization: String?
)