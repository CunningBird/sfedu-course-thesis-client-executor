package com.cunningbird.thesis.client.executor.main.view.services.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentServicesDetailsBinding
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository
import com.cunningbird.thesis.client.executor.main.view.FragmentViewModelFactory
import com.cunningbird.thesis.client.executor.main.view.MainActivity

class ServiceDetailsFragment : Fragment() {

    private lateinit var binding: FragmentServicesDetailsBinding

    val viewModel: ServiceDetailsViewModel by viewModels {
        FragmentViewModelFactory(
            mainActivity.application,
            mainActivity.viewModel.backendRepository,
            BackendRepository::class.java
        )
    }

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentServicesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
    }

    private fun setViews() {
        mainActivity.changeToolbar(getString(R.string.service_details), true)
    }

    private fun setListeners() {
        binding.actionButton.setOnClickListener {
            val arg = bundleOf(
                "title" to binding.tvServiceName.text.toString(),
                "price" to binding.tvServiceAmount.text.toString()
            )
            findNavController().navigate(
                R.id.action_serviceDetailsFragment_to_editServiceFragment,
                arg
            )
        }
    }
}