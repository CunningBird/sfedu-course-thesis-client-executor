package com.cunningbird.thesis.client.executor.main.view.chats.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.cunningbird.thesis.client.executor.databinding.ItemMessageBinding
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Chat
import java.text.SimpleDateFormat

class ChatsListAdapter(private val onClickEvent: (p: Int) -> Unit) :
    RecyclerView.Adapter<ChatsListAdapter.MessagesViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("yyyy.mm.dd hh:mm")

    var list: List<Chat> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val binding = ItemMessageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MessagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.image.load(list[position].executorAvatar) {
            transformations(CircleCropTransformation())
        }
        holder.name.text = list[position].executorName.toString()
        holder.description.text = list[position].messages.last().text

        holder.apply {
            val onClickEvent = View.OnClickListener { onClickEvent(adapterPosition) }
            image.setOnClickListener(onClickEvent)
            name.setOnClickListener(onClickEvent)
            description.setOnClickListener(onClickEvent)
        }
    }

    override fun getItemCount() = list.size

    inner class MessagesViewHolder(binding: ItemMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.cvUserImg
        val name = binding.tvUserName
        val description = binding.tvUserDescription
    }
}