package com.cunningbird.thesis.client.executor.main.data.client

import com.cunningbird.thesis.client.executor.main.domain.entities.chat.ChatList
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Message
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.SendMessageRequest
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Chat
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface BackendClient {

    @GET("chats")
    fun getChats(): Call<ChatList>

    @GET("chats/{id}")
    fun getChatById(
        @Path("id") id: UUID,
    ): Call<Chat>

    @POST("chats/{chatId}")
    fun sendMessage(
        @Path("chatId") serviceId: UUID,
        @Body request: SendMessageRequest
    ): Call<Message>
}