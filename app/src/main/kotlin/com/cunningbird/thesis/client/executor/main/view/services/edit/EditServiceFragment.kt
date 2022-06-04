package com.cunningbird.thesis.client.executor.main.view.services.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentServicesEditBinding
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository
import com.cunningbird.thesis.client.executor.main.view.FragmentViewModelFactory
import com.cunningbird.thesis.client.executor.main.view.MainActivity

class EditServiceFragment : Fragment() {

    private lateinit var binding: FragmentServicesEditBinding

    val viewModel: EditServiceViewModel by viewModels {
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
        binding = FragmentServicesEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setViews()
    }

    private fun setViews() {
        mainActivity.changeToolbar(getString(R.string.edit_service), true)

        val arguments = requireArguments()
        binding.etTitle.setText(arguments.getString("title"))
        binding.etPrice.setText(arguments.getString("price"))

    }

    private fun setListeners() {
        binding.actionButton.setOnClickListener {

            if (binding.etTitle.text.toString() != requireArguments().getString("title")
                || binding.etPrice.text.toString() != requireArguments().getString("price")
            ) {
                Toast.makeText(requireContext(), "Здесь что-то произошло", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}