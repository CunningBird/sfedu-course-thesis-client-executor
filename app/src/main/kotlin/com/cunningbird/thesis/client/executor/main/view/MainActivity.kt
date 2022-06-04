package com.cunningbird.thesis.client.executor.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.cunningbird.thesis.client.executor.R
import com.cunningbird.thesis.client.executor.databinding.ActivityMainBinding

/**
 * Отправная точка основного приложения
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(application, intent.getStringExtra("accessToken")!!, intent.getStringExtra("userId")!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionBarMain.backButton.setOnClickListener {
            onBackPressed()
        }

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
    }

    fun changeToolbar(title: String, isButtonActive: Boolean) {
        binding.actionBarMain.backButton.isVisible = isButtonActive
        binding.actionBarMain.actionBarTab.text = title
    }

    fun checkBinding() = this::binding.isInitialized
}