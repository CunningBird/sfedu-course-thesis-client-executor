package com.cunningbird.thesis.client.executor.main.view.services.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentServicesListBinding
import com.cunningbird.thesis.client.executor.main.domain.entities.service.Service
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository
import com.cunningbird.thesis.client.executor.main.view.FragmentViewModelFactory
import com.cunningbird.thesis.client.executor.main.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesFragment : Fragment() {

    private lateinit var binding: FragmentServicesListBinding

    val viewModel: ServicesViewModel by viewModels {
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
        binding = FragmentServicesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity.changeToolbar(getString(R.string.your_services), false)

        val adapter = ServiceAdapter(this::onServiceClick)
        binding.rvServices.adapter = adapter

//        // TODO optimize this
//        viewModel.getServices().enqueue(object : Callback<List<Service>> {
//            override fun onFailure(call: Call<List<Service>>, t: Throwable) {
//                Log.d("MainActivity", "Request Failed: $call")
//            }
//
//            override fun onResponse(call: Call<List<Service>>, response: Response<List<Service>>) {
//                val services = response.body()
//
//                if (!response.isSuccessful || services == null) {
//                    Log.d("MainActivity", "Request Failed: $call")
//                } else {
//                    Log.d("MainActivity", "Request Success")
//                    adapter.list = services
//                }
//            }
//        })
    }

    private fun onServiceClick(i: Int) {
        val arg = bundleOf(SERVICE_POSITION to i)
        findNavController().navigate(R.id.action_navigation_services_to_serviceDetailsFragment, arg)
    }

    companion object {
        const val SERVICE_POSITION = "SERVICE_POSITION"
    }
}