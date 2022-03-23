package com.pajaga.service.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pajaga.R
import com.pajaga.ui.main.MainActivity
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant


private const val CHANNEL_ID = "my_channel"

class FirebaseService: FirebaseMessagingService() {

    private lateinit var pathNotif: String

    companion object {
        var sharedPref: SharedPreferences? = null

        var token: String?
            get() {
                return SavedData.getString(Constant.KEY_TOKEN)
            }
            set(value) {
                value?.let { SavedData.setString(Constant.KEY_TOKEN, it) }
            }
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token = newToken
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

//        pathNotif = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.notification
//
//        val intent = Intent(this, MainActivity::class.java)
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationID = Random.nextInt()
//
//        showLogAssert("message", "${message.data}")
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationChannel(notificationManager)
//        }
//
//        val testSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle(message.data["title"])
//            .setContentText(message.data["message"])
//            .setSmallIcon(R.drawable.ic_baseline_add_24)
//            .setAutoCancel(true)
//            .setSound(testSound)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        notificationManager.notify(notificationID, notification)
        sendNotification(message)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createNotificationChannel(notificationManager: NotificationManager) {
//        val testSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//        showLogAssert("pathNotif", pathNotif)
////        val builder = AudioAttributes.Builder()
////        builder.setUsage(AudioAttributes.USAGE_NOTIFICATION)
//        val importance = NotificationManager.IMPORTANCE_HIGH
//        val audioAttributes = AudioAttributes.Builder()
//            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
//            .build()
//        val channelName = "channelName"
//        val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
//            description = "My channel description"
//            enableLights(true)
//            enableVibration(true)
//
//            lightColor = Color.GREEN
//            setSound(testSound, audioAttributes)
//        }
//        notificationManager.createNotificationChannel(channel)
//    }

    private fun sendNotification(message: RemoteMessage) {
        val textTitle: String = message.data["title"].toString()
        val alert: String = message.data["message"].toString()
//        val alert: String = notificationBean.getMessage().getAlert()
//        val orderId: Int = notificationBean.getMessage().getOrderId()
        val notificationType: String? = message.messageType
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val soundUri: Uri
        val channelName: String
        if (notificationType == "Pending") {
            channelName = "test"
            soundUri =
                Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.notification)
        } else {
            channelName = "test 1"
            soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, channelName)
            .setSmallIcon(R.drawable.ic_baseline_add_24)
            .setContentTitle(textTitle)
            .setContentText(alert)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.app_name)
            val description = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelName, name, importance)
            channel.description = description
            val attributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.setSound(soundUri, attributes)

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
        val notificationManager = NotificationManagerCompat.from(this)

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(101, mBuilder.build())
    }
}