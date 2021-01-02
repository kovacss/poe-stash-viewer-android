package com.example.myapplication.repository

import okhttp3.*


// Based on https://medium.com/mindorks/efficient-android-testing-using-wiremock-and-espresso-cf2d7b7c735f
object UrlChangingInterceptor: Interceptor {
    private var httpUrl: HttpUrl? = null

    fun setHttpUrl(url: String) {
        httpUrl = HttpUrl.parse(url)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var original: Request = chain.request()

        if (httpUrl != null) {
            original = original.newBuilder()
                .url(httpUrl)
                .build()
        }
        return chain.proceed(original)
    }
}

fun getHttpClient(): OkHttpClient {
    val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

    httpClient.addInterceptor(UrlChangingInterceptor)
    return httpClient.build()
}