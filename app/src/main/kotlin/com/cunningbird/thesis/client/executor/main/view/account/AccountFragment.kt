package com.cunningbird.thesis.client.executor.main.view.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.FragmentAccountBinding
import com.cunningbird.thesis.client.executor.main.view.MainActivity

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    val viewModel: AccountViewModel by viewModels()

    private lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = (activity as MainActivity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.changeToolbar(getString(R.string.update_account_information),false)
    }

}