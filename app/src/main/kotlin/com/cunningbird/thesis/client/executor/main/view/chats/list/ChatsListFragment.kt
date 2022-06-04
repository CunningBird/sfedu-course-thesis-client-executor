package com.cunningbird.thesis.client.executor.main.view.chats.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.ChatList
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentChatsListBinding
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository
import com.cunningbird.thesis.client.executor.main.view.FragmentViewModelFactory
import com.cunningbird.thesis.client.executor.main.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatsListFragment : Fragment() {

    private lateinit var binding: FragmentChatsListBinding

    private val viewModel: ChatsListViewModel by viewModels {
        FragmentViewModelFactory(
            mainActivity.application,
            mainActivity.viewModel.backendRepository,
            BackendRepository::class.java
        )
    }

    private lateinit var mainActivity: MainActivity

    private lateinit var adapter: ChatsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mainActivity.checkBinding()) {
            mainActivity.changeToolbar(getString(R.string.your_messages), false)
        }

        adapter = ChatsListAdapter(this::onChatClick)
        binding.rvMessages.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMessages.adapter = adapter

        viewModel.getChats().enqueue(object : Callback<ChatList> {
            override fun onFailure(call: Call<ChatList>, t: Throwable) {
                Log.d("MainActivity", "Request Failed: $call")
            }

            override fun onResponse(call: Call<ChatList>, response: Response<ChatList>) {
                val services = response.body()

                if (!response.isSuccessful || services == null) {
                    Log.d("MainActivity", "Request Failed: $call")
                } else {
                    Log.d("MainActivity", "Request Success")
                    adapter.list = services.list!!
                }
            }
        })
    }

    private fun onChatClick(i: Int) {
        val id = adapter.list[i].id.toString()
        val arg = bundleOf("chatId" to id)
        findNavController().navigate(R.id.action_chatsListFragment_to_ChatsDialogFragment, arg)
    }
}