package com.cunningbird.thesis.client.executor.main.domain.entities.chat

import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Message
import com.squareup.moshi.Json
import java.util.*

data class Chat(
    @field:Json(name = "id") var id: UUID? = null,
    @field:Json(name = "customerId") var customerId: UUID? = null,
    @field:Json(name = "executorId") var executorId: UUID? = null,
    @field:Json(name = "customerAvatar") val customerAvatar: String? = null,
    @field:Json(name = "executorAvatar") val executorAvatar: String? = null,
    @field:Json(name = "customerName") val customerName: String? = null,
    @field:Json(name = "executorName") val executorName: String? = null,
    @field:Json(name = "messages") var messages: MutableList<Message> = mutableListOf()
)