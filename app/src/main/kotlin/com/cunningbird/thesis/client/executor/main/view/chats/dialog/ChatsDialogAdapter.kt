package com.cunningbird.thesis.client.executor.main.view.chats.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cunningbird.thesis.client.executor.main.domain.entities.chat.Message
import com.cunningbird.thesis.client.executor.databinding.ItemFromMessageBinding
import com.cunningbird.thesis.client.executor.databinding.ItemMessageDateHeaderBinding
import com.cunningbird.thesis.client.executor.databinding.ItemToMessageBinding

class ChatsDialogAdapter(private val authorId: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<Message> = arrayListOf()

    companion object {
        const val DATE = 0
        const val FROM = 1
        const val TO = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FROM -> {
                val binding = ItemFromMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MessageFromViewHolder(binding)
            }

            TO -> {
                val binding = ItemToMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MessageToViewHolder(binding)
            }

            else -> {
                val binding = ItemMessageDateHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MessageDateViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageFromViewHolder -> holder.bind(list[position])
            is MessageToViewHolder -> holder.bind(list[position])
            is MessageDateViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        val author = list[position].authorId.toString()
        return if (author != authorId) {
            TO
        } else if (author == authorId) {
            FROM
        } else {
            DATE
        }
    }

    inner class MessageFromViewHolder(val binding: ItemFromMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvText.text = message.text
            binding.tvTime.text = message.date.toString() // TODO format this
        }
    }

    inner class MessageToViewHolder(val binding: ItemToMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvText.text = message.text
            binding.tvTime.text = message.date.toString() // TODO format this
        }
    }

    inner class MessageDateViewHolder(val binding: ItemMessageDateHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvDate.text = message.date.toString() // TODO format this
        }
    }
}