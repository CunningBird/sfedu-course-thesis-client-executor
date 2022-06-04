package com.cunningbird.thesis.client.executor.auth.data.client

import com.cunningbird.thesis.client.executor.auth.domain.entities.OAuth2AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthClient {

    @FormUrlEncoded
    @POST("{path}")
    fun requestAccessToken(
        @Path("path") path: String,
        @Field("client_id") clientID: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("grant_type") grantType: String
    ): Call<OAuth2AccessToken>
}
