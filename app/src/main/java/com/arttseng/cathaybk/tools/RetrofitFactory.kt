package com.arttseng.cathaybk.tools

import android.util.Log
import com.arttseng.cathaybk.MyApplication
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory
{
    object WebAccess {

        val API : ApiClient by lazy {
            Log.d("WebAccess", "Creating retrofit client")
            val retrofit = Retrofit.Builder()
                .client(MyApplication.getOkHttpClient())
                .baseUrl(Const.BaseURL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return@lazy retrofit.create(ApiClient::class.java)
        }
    }
}