package com.cunningbird.thesis.client.executor.main.view.chats.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Message
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.SendMessageRequest
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Chat
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository
import retrofit2.Call
import java.util.*

class ChatsDialogViewModel(application: Application, private val repository: BackendRepository) :
    AndroidViewModel(application) {

    fun getUserId(): String {
        return repository.getUserId()
    }

    fun getChatById(id: UUID): Call<Chat> {
        return repository.getChat(id)
    }

    fun sendMessage(chatId: UUID, text: String, date: Date): Call<Message> {
        val request = SendMessageRequest(text, date)
        return repository.sendMessage(chatId, request)
    }
}