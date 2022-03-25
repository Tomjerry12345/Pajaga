package com.pajaga.database.retrofit

import com.pajaga.utils.other.Constant.BASE_URL_FCM
import com.pajaga.utils.other.Constant.BASE_URL_NEWS
import com.pajaga.utils.other.Constant.BASE_URL_NEWS_API
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
                .baseUrl(BASE_URL_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiNews by lazy {
            retrofitNews.create(NewsAPI::class.java)
        }

        private val retrofitNewsAPI by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL_NEWS_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiNewsAPI by lazy {
            retrofitNewsAPI.create(NewNewsAPI::class.java)
        }
    }
}