package com.cunningbird.thesis.client.executor.main.utils.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.util.*

class UuidJsonAdapter {
    @ToJson
    fun toJson(value: UUID?) = value?.toString()
    @FromJson
    fun fromJson(input: String) = UUID.fromString(input)
}