package com.pajaga.database.retrofit

import com.pajaga.model.NewNewsModel
import com.pajaga.model.NewsModel
import com.pajaga.utils.other.Constant
import retrofit2.Response
import retrofit2.http.GET

interface NewNewsAPI {

    //    @Headers("Authorization: key=$API_KEY_NEWS", "Content-Type:$CONTENT_TYPE")
//    @Headers( "Content-Type:$CONTENT_TYPE")
    @GET("/v2//top-headlines?country=id&category=business&apiKey=267aa85cedbb4866a7f98184c4d8150c")
//    @GET("/api/1/news")
    suspend fun getNews(
    ): Response<NewNewsModel>
}