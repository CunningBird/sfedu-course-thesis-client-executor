package com.cunningbird.thesis.client.executor.main.domain.repository

import com.cunningbird.thesis.client.executor.main.domain.entities.chat.ChatList
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Message
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.SendMessageRequest
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Chat
import retrofit2.Call
import java.util.*

interface BackendRepository {

    fun getChats(): Call<ChatList>

    fun getChat(id: UUID): Call<Chat>

    fun sendMessage(chatId: UUID, request: SendMessageRequest): Call<Message>

    fun getUserId(): String

    fun logout(): Call<Void>
}