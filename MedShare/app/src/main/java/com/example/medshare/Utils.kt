package com.example.medshare

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {

    @SuppressLint("NewApi")
    private val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
    @SuppressLint("NewApi")
    private val outputFormatter = DateTimeFormatter.ofPattern("h:mm a, d MMM")

    @SuppressLint("NewApi")
    fun formatDateTime(timestamp: String): String {
        return try {
            val dateTime = LocalDateTime.parse(timestamp, inputFormatter)
            dateTime.format(outputFormatter)
        } catch (e: Exception) {
            "Invalid Date" // Handle parsing errors
        }
    }
}