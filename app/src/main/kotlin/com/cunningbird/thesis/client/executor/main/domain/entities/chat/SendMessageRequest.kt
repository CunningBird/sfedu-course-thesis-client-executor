package com.cunningbird.thesis.client.executor.main.domain.entities.chat

import com.squareup.moshi.Json
import java.util.*

data class SendMessageRequest(
    @field:Json(name = "text") val text: String? = null,
    @field:Json(name = "date") var date: Date? = null,
)