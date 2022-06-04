package com.cunningbird.thesis.client.executor.main.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.cunningbird.thesis.client.executor.main.data.repository.BackendRepositoryImpl
import com.cunningbird.thesis.client.executor.main.domain.repository.BackendRepository

class MainViewModel(application: Application, accessToken: String, userId: String) : AndroidViewModel(application) {

    val backendRepository: BackendRepository = BackendRepositoryImpl(accessToken, userId)
}