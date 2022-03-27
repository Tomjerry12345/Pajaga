package com.pajaga.service.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pajaga.R
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant
import com.pajaga.utils.other.showLogAssert
import kotlin.random.Random

private const val CHANNEL_ID = "my_channel"

class FirebaseService : FirebaseMessagingService() {

    companion object {
//        var sharedPref: SharedPreferences? = null

        var token: String?
            get() {
                return SavedData.getString(Constant.KEY_TOKEN)
            }
            set(value) {
                value?.let { SavedData.setString(Constant.KEY_TOKEN, it) }
            }

        var mediaPlayer: MediaPlayer? = null
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token = newToken
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val latitude = SavedData.getFloat(Constant.KEY_LATITUDE)
        val longitude = SavedData.getFloat(Constant.KEY_LONGITUDE)
        mediaPlayer = MediaPlayer.create(this, R.raw.notification)
        mediaPlayer?.start()

//        pathNotif = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/" + R.raw.notification
//
//        val intent = Intent(this, MainActivity::class.java)
        // Create a Uri from an intent string. Use the result to create an Intent.
//        val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
        val testLatitude = "-5.2057715"
        val testLongitude = "119.4951314"
        val gmmIntentUri = Uri.parse("google.navigation:q=$testLatitude,$testLongitude")

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
// Make the Intent explicit by setting the Google Maps package
        intent.setPackage("com.google.android.apps.maps")
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        showLogAssert("message", "${message.data}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
//            mediaPlayer.stop()
            addNextIntentWithParentStack(intent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["message"])
            .setSmallIcon(R.drawable.ic_baseline_add_24)
            .setAutoCancel(true)
            .setContentIntent(resultPendingIntent)
            .build()

        notificationManager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "My channel description"
            enableLights(true)
            enableVibration(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }


}