package com.pajaga.model

import com.pajaga.utils.other.Constant

data class NotificationData(
    val title: String,
    val message: String
)

data class Notification(
    val title: String,
    val body: String,
    val android_channel_id: String = Constant.CHANNEL_ID,
    val sound : String = "notification"
)

data class PushNotification(
    val data: NotificationData,
    val to: String,
    val notification: Notification
)