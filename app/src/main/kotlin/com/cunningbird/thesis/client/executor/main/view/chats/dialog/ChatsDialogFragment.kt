package com.cunningbird.thesis.client.executor.main.view.chats.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Message
import com.cunningbird.thesis.client.executor.databinding.FragmentChatsDialogBinding
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Chat
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository
import com.cunningbird.thesis.client.executor.main.view.FragmentViewModelFactory
import com.cunningbird.thesis.client.executor.main.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChatsDialogFragment : Fragment() {

    private lateinit var binding: FragmentChatsDialogBinding

    private val viewModel: ChatsDialogViewModel by viewModels {
        FragmentViewModelFactory(
            mainActivity.application,
            mainActivity.viewModel.backendRepository,
            BackendRepository::class.java
        )
    }

    private lateinit var mainActivity: MainActivity

    private lateinit var adapter: ChatsDialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = UUID.fromString(arguments!!.get("chatId") as String)
        val authorId = viewModel.getUserId()

        adapter = ChatsDialogAdapter(authorId)
        binding.rvMessages.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMessages.adapter = adapter

        viewModel.getChatById(id).enqueue(object : Callback<Chat> {
            override fun onFailure(call: Call<Chat>, t: Throwable) {
                Log.d("MainActivity", "Request Failed: $call")
            }

            override fun onResponse(call: Call<Chat>, response: Response<Chat>) {
                val chat = response.body()

                if (!response.isSuccessful || chat == null) {
                    Log.d("MainActivity", "Request Failed: $call")
                } else {
                    Log.d("MainActivity", "Request Success")

                    mainActivity.changeToolbar("Dialog with ${chat.executorName}", true)
                    adapter.list = chat.messages
                    adapter.notifyItemChanged(adapter.list.lastIndex)
                    binding.rvMessages.scrollToPosition(adapter.list.lastIndex)
                }
            }
        })

        binding.buttonSendMessage.setOnClickListener {
            val text = binding.etChat.text.toString()

            viewModel.sendMessage(id, text, Date()).enqueue(object : Callback<Message> {
                override fun onFailure(call: Call<Message>, t: Throwable) {
                    Log.d("MainActivity", "Request Failed: $call")
                }

                override fun onResponse(call: Call<Message>, response: Response<Message>) {
                    val message = response.body()

                    if (!response.isSuccessful || message == null) {
                        Log.d("MainActivity", "Request Failed: $call")
                        onMessageComing( // TODO Delete this
                            Message(
                                UUID.randomUUID(),
                                UUID.randomUUID(),
                                text,
                                Date(),
                            )
                        )
                    } else {
                        Log.d("MainActivity", "Request Success")
                        onMessageComing(
                            Message(
                                message.id,
                                message.authorId,
                                message.text,
                                message.date,
                            )
                        )
                    }
                    binding.etChat.text?.clear()
                }
            })
        }
    }

    private fun onMessageComing(message: Message) {
        adapter.list.add(message)
        adapter.notifyItemInserted(adapter.list.lastIndex)
        binding.rvMessages.scrollToPosition(adapter.list.lastIndex)
    }

    fun onMessageUpdate(position: Int, message: Message) {
        adapter.list[position] = message
        adapter.notifyItemChanged(position, message)
    }

    fun onMessageDeleted(position: Int) {
        adapter.list.removeAt(position)
        adapter.notifyItemRemoved(position)
    }
}