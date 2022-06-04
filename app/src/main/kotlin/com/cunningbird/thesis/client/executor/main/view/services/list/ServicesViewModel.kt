package com.cunningbird.thesis.client.executor.main.view.services.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository

class ServicesViewModel(application: Application, private val repository: BackendRepository) :
    AndroidViewModel(application) {
}