package com.cunningbird.thesis.client.executor.main.view.appointments.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cunningbird.thesis.client.executor.main.view.appointments.monthly.AppointmentsFragment
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentAppointmentsScheduleBinding
import com.cunningbird.thesis.client.executor.main.view.MainActivity
import java.util.*

class AppointmentScheduleFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsScheduleBinding

    val viewModel: AppointmentScheduleViewModel by viewModels()

    lateinit var date: List<Int>

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAppointmentsScheduleBinding.inflate(inflater, container, false)
        val arg = arguments?.getString(AppointmentsFragment.SCHEDULE_DAY)
        date = arg!!.split(" ").map { it.toInt() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
    }

    private fun setViews() {
        setToolbar()
    }

    private fun setToolbar() {
        val currentDate = requireContext().resources.getString(R.string.appointments_at) +
                if (Locale.getDefault().language == "RU")
                    " ${date[0]} ${date[1] + 1} ${date[2]}"
                else
                    " ${date[1] + 1} ${date[0]} ${date[2]}"
        mainActivity.changeToolbar(currentDate, true)
    }

    private fun setListeners() {

    }
}