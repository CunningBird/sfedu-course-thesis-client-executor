package com.cunningbird.thesis.client.executor.main.view.services.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository

class EditServiceViewModel(application: Application, private val repository: BackendRepository) :
    AndroidViewModel(application) {
}