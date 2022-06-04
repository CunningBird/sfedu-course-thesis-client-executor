package com.cunningbird.thesis.client.executor.main.view.appointments.daily

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cunningbird.thesis.client.executor.main.domain.entities.appointment.Appointment
import com.cunningbird.thesis.client.executor.databinding.ItemAppointmentBinding

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    var list: List<Appointment> = arrayListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemAppointmentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        with(holder) {
            when (position) {
                0 -> upperSeparator.isVisible = false
                itemCount - 1 -> lowerSeparator.isVisible = false
            }

        }
    }

    override fun getItemCount() = list.size

    inner class ScheduleViewHolder(binding: ItemAppointmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val upperSeparator = binding.upperSeparator
        val lowerSeparator = binding.lowerSeparator
        val itemContainer = binding.llItem
        val time = binding.tvTime
    }
}