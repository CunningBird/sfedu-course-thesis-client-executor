package com.cunningbird.thesis.client.executor.auth.data.repository

import android.content.SharedPreferences
import android.net.Uri
import com.squareup.moshi.Moshi
import com.cunningbird.thesis.client.executor.auth.data.client.AuthClient
import com.cunningbird.thesis.client.executor.auth.domain.entities.OAuth2AccessToken
import com.cunningbird.thesis.client.executor.auth.domain.repository.AuthRepository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AuthRepositoryImpl(
    private val mSharedPreferences: SharedPreferences,
) : AuthRepository {

    private val client: AuthClient

    private val baseUrl = "http://185.46.11.94:8080/oauth2/"
    private val clientID = "executor-application"
    private val clientSecret = "secret1"
    private val redirectURI = "http://samplecallback.com/"
    private val scope = "openid executor"

    private val accessTokenPreferencesKey = "OAuth2AccessToken"

    private val tokenAdapter = Moshi.Builder().build().adapter(OAuth2AccessToken::class.java)

    init {
        val builder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl).build()

        client = builder.create(AuthClient::class.java)
    }

    override fun getAuthorizationUrl(): Uri {
        return Uri.parse(baseUrl + "authorize").buildUpon()
            .appendQueryParameter("client_id", clientID)
            .appendQueryParameter("redirect_uri", redirectURI)
            .appendQueryParameter("scope", scope)
            .appendQueryParameter("response_type", "code")
            .build()
    }

    override fun requestAccessToken(code: String): Call<OAuth2AccessToken> {
        return client.requestAccessToken(
            path = "token",
            clientID = clientID,
            clientSecret = clientSecret,
            redirectUri = redirectURI,
            code = code,
            grantType = "authorization_code"
        )
    }

    override fun saveAccessToken(accessToken: OAuth2AccessToken) {
        mSharedPreferences.edit().putString(accessTokenPreferencesKey, tokenAdapter.toJson(accessToken)).apply()
    }

    override fun getAccessToken(): OAuth2AccessToken? {
        val storedJson = mSharedPreferences.getString(accessTokenPreferencesKey, null)

        return if (storedJson != null) {
            tokenAdapter.fromJson(storedJson)
        } else {
            null
        }
    }

    override fun removeAccessToken() {
        mSharedPreferences.edit().remove(accessTokenPreferencesKey).apply()
    }

    override fun hasAccessToken(): Boolean {
        return mSharedPreferences.contains(accessTokenPreferencesKey)
    }
}