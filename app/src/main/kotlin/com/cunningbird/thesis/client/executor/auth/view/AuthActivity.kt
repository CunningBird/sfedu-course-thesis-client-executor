package com.cunningbird.thesis.client.executor.auth.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cunningbird.thesis.client.executor.auth.domain.entities.OAuth2AccessToken
import com.cunningbird.thesis.client.executor.databinding.ActivityAuthBinding
import com.cunningbird.thesis.client.executor.main.view.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Отправная точка авторизации
 */
class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    val viewModel: AuthViewModel by viewModels()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wvAuth.clearCache(true)
        binding.wvAuth.settings.javaScriptEnabled = true
        binding.wvAuth.settings.javaScriptCanOpenWindowsAutomatically = true

        // TODO disable cookies
        binding.wvAuth.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                Toast.makeText(binding.wvAuth.context, "FAILED", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val code = request?.url?.getQueryParameter("code")

                if (code != null) {
                    viewModel.requestAccessToken(code).enqueue(object : Callback<OAuth2AccessToken> {
                        override fun onFailure(call: Call<OAuth2AccessToken>, t: Throwable) {
                            Toast.makeText(binding.wvAuth.context, "FAILED", Toast.LENGTH_SHORT).show()
                            finish()
                        }

                        override fun onResponse(call: Call<OAuth2AccessToken>, response: Response<OAuth2AccessToken>) {
                            val accessToken = response.body()
                            if (!response.isSuccessful || accessToken == null) {
                                Toast.makeText(binding.wvAuth.context, "FAILED", Toast.LENGTH_SHORT).show()
                                finish()
                            } else {
                                Log.d("OAuth", "AccessToken: ${accessToken.accessToken}")
                                viewModel.saveAccessToken(accessToken)

                                Toast.makeText(binding.wvAuth.context, "LOGGED", Toast.LENGTH_SHORT).show()

                                val intent = Intent(binding.wvAuth.context, MainActivity::class.java)
                                intent.putExtra("accessToken", accessToken.accessToken)
                                intent.putExtra("userId", "bbe0fe62-38d1-11ec-8d3d-0242ac999998") // TODO get ID from auth server
                                startActivity(intent)
                                finish()
                            }
                        }
                    })

                    return true
                }

                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        binding.wvAuth.loadUrl(viewModel.getAuthUrl())
    }
}