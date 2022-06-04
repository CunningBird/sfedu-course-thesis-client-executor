package com.cunningbird.thesis.client.executor.auth.view

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.cunningbird.thesis.client.executor.auth.data.repository.AuthRepositoryImpl
import com.cunningbird.thesis.client.executor.auth.domain.entities.OAuth2AccessToken
import com.cunningbird.thesis.client.executor.auth.domain.repository.AuthRepository
import retrofit2.Call

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val authRepository: AuthRepository = AuthRepositoryImpl(application.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE))

    fun requestAccessToken(code: String): Call<OAuth2AccessToken> {
        return authRepository.requestAccessToken(code)
    }

    fun saveAccessToken(token: OAuth2AccessToken) {
        authRepository.saveAccessToken(token)
    }

    fun getAuthUrl(): String {
        return authRepository.getAuthorizationUrl().toString()
    }
}