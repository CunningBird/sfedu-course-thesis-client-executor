package com.cunningbird.thesis.client.executor.main.domain.entities.chat

import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Chat
import com.squareup.moshi.Json

data class ChatList(
    @field:Json(name = "list") var list: List<Chat>? = null,
)