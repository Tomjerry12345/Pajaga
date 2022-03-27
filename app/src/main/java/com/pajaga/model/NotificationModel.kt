package com.pajaga.model

data class NotificationData(
    val title: String,
    val message: String
)

data class Notification(
    val title: String,
    val body: String,
    val android_channel_id: String
//    val sound : String
)

data class PushNotification(
    val data: NotificationData,
    val to: String,
    val notification: Notification
)