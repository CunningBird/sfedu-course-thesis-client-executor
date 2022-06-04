package com.cunningbird.thesis.client.executor.main.view.appointments.monthly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentAppointmentsListBinding
import com.cunningbird.thesis.client.executor.main.view.MainActivity

class AppointmentsFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsListBinding

    val viewModel: AppointmentsViewModel by viewModels()

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAppointmentsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
    }

    private fun setViews() {
        if (mainActivity.checkBinding()) { // TODO fix this
            mainActivity.changeToolbar(getString(R.string.your_appointments), false)
        }
        //mainActivity.changeToolbar(getString(R.string.your_appointments), false)
    }

    private fun setListeners() {
        binding.calendar.setOnDateChangeListener { calendarView, i, i2, i3 ->
            val day = "$i3 $i2 $i"
            val arg = bundleOf(SCHEDULE_DAY to day)
            findNavController().navigate(R.id.action_navigation_appointments_to_appointmentScheduleFragment, arg)
        }
    }

    companion object {
        const val SCHEDULE_DAY = "SCHEDULE_DAY"
    }
}