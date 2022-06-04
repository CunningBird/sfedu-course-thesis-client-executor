package com.cunningbird.thesis.client.executor.auth.domain.repository

import android.net.Uri
import com.cunningbird.thesis.client.executor.auth.domain.entities.OAuth2AccessToken
import retrofit2.Call

interface AuthRepository {

    fun getAuthorizationUrl(): Uri

    fun requestAccessToken(code: String): Call<OAuth2AccessToken>

    fun saveAccessToken(accessToken: OAuth2AccessToken)

    fun getAccessToken(): OAuth2AccessToken?

    fun removeAccessToken()

    fun hasAccessToken(): Boolean
}