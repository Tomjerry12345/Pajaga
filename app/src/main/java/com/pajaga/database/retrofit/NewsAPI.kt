package com.pajaga.database.retrofit

import com.pajaga.model.NewsModel
import com.pajaga.utils.other.Constant.API_KEY_NEWS
import com.pajaga.utils.other.Constant.CONTENT_TYPE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface NewsAPI {

//    @Headers("Authorization: key=$API_KEY_NEWS", "Content-Type:$CONTENT_TYPE")
//    @Headers( "Content-Type:$CONTENT_TYPE")
    @GET("/api/1/news?apikey=${API_KEY_NEWS}&country=id")
//    @GET("/api/1/news")
    suspend fun getNews(
    ): Response<NewsModel>
}