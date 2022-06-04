package com.cunningbird.thesis.client.executor.main.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FragmentViewModelFactory(
    private val application: Application,
    private val repository: Any,
    private val repositoryClass: Class<*>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Application::class.java, repositoryClass).newInstance(application, repository)
    }
}