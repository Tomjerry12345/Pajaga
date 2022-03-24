package com.pajaga.database.retrofit

import com.pajaga.utils.other.Constant.BASE_URL_FCM
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance() {

    companion object {
        private val retrofitNotif by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_FCM)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiNotif by lazy {
            retrofitNotif.create(NotificationAPI::class.java)
        }

        private val retrofitNews by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_FCM)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiNews by lazy {
            retrofitNews.create(NewsAPI::class.java)
        }
    }
}