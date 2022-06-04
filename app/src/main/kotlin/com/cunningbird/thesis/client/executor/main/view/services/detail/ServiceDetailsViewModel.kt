package com.cunningbird.thesis.client.executor.main.view.services.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository

class ServiceDetailsViewModel(application: Application, private val repository: BackendRepository) :
    AndroidViewModel(application) {
}