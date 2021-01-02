package com.example.myapplication.repository

import com.example.myapplication.model.dto.StashTabResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

fun getPOEService(): POEService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.pathofexile.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(getHttpClient())
        .build()

    return retrofit.create(POEService::class.java)
}

interface POEService {

    // TODO extract cookie
    @Headers(value = [
        "Cookie: POESESSID=YOUR_POE_SESSID"
    ])
    @GET("character-window/get-stash-items?tabs=1")
    fun getStashTabs(
        @Query("league") league: String,
        @Query("accountName") accountName: String
    ): Call<StashTabResponse>?

    @GET("character-window/get-stash-items")
    fun getStashItems(
        @Query("league") league: String,
        @Query("accountName") accountName: String,
        @Query("tabIndex") tabIndex: String
    ): Call<StashTabResponse>?
}