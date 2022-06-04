package com.cunningbird.thesis.client.executor.auth.domain.entities

import com.squareup.moshi.Json

data class OAuth2AccessToken(
    @field:Json(name = "access_token") val accessToken: String,
    @field:Json(name = "scope") val scope: String,
    @field:Json(name = "id_token") val idToken: String,
    @field:Json(name = "token_type") val tokenType: String,
    @field:Json(name = "expires_in") val expiresIn: Int
)