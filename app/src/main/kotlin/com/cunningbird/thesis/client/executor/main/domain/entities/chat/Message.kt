package com.cunningbird.thesis.client.executor.main.domain.entities.chat

import com.squareup.moshi.Json
import java.util.*

data class Message(
    @field:Json(name = "id") var id: UUID? = null,
    @field:Json(name = "authorId") var authorId: UUID? = null,
    @field:Json(name = "text") val text: String? = null,
    @field:Json(name = "date") val date: Date? = null
)
