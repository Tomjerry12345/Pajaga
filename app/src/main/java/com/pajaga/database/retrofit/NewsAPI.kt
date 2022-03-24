package com.pajaga.database.retrofit

import com.pajaga.model.PushNotification
import com.pajaga.utils.other.Constant.CONTENT_TYPE
import com.pajaga.utils.other.Constant.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NewsAPI {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}