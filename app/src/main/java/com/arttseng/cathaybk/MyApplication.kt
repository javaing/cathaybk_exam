package com.arttseng.cathaybk

import android.app.Application
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class MyApplication: Application() {

    companion object {
        private var instance: Application? = null
        fun instance() = instance!!

        private var okHttpClient: OkHttpClient? = null

        fun getOkHttpClient(): OkHttpClient {
            if(okHttpClient==null) {
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BODY
                    okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .retryOnConnectionFailure(true) //.addInterceptor(UserAgentInterc)
                        .connectTimeout(80, TimeUnit.SECONDS)
                        .readTimeout(80, TimeUnit.SECONDS)
                        .build()
                } else {
                    okHttpClient = OkHttpClient.Builder()
                        .retryOnConnectionFailure(true) //.addInterceptor(UserAgentInterc)
                        .connectTimeout(80, TimeUnit.SECONDS)
                        .readTimeout(80, TimeUnit.SECONDS)
                        .build()
                }
            }
            return okHttpClient as OkHttpClient
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


}