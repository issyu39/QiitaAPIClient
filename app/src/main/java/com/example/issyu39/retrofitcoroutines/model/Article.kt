package com.example.issyu39.retrofitcoroutines.model

import android.util.Log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class Article(
    val title: String,
    @SerialName("created_at")
    val createdAt: String,
    val user: User,
) {
    fun convertToDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.JAPAN)
        val date = try {
            sdf.parse(this.createdAt)
        } catch (e: ParseException) {
            Log.e("exception", e.toString())
        }
        return SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN).format(date)
    }
}