package com.cunningbird.thesis.client.executor.main.view.appointments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentAppointmentsDetailsBinding
import com.cunningbird.thesis.client.executor.main.view.MainActivity

class AppointmentDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsDetailsBinding

    val viewModel: AppointmentDetailsViewModel by viewModels()

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAppointmentsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
    }

    private fun setViews() {
        mainActivity.changeToolbar(getString(R.string.appointment_details), true)
    }

    private fun setListeners() {

    }
}