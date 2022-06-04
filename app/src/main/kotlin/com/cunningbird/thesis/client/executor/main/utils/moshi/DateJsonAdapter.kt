package com.cunningbird.thesis.client.executor.main.utils.moshi

import android.annotation.SuppressLint
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DateJsonAdapter {

    @SuppressLint("SimpleDateFormat")
    private val datePattern = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")

    @ToJson
    fun toJson(value: Date?) = value?.toString()

    @FromJson
    fun fromJson(input: String) = datePattern.parse(input)
}