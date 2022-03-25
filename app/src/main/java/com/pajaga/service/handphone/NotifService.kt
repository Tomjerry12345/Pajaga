package com.pajaga.service.handphone

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.media.VolumeProviderCompat
import com.pajaga.model.NotificationData
import com.pajaga.model.PushNotification
import com.pajaga.service.firebase.FirebaseService
import com.pajaga.ui.TestingActivity
import com.pajaga.ui.main.home.HomeViewModel
import com.pajaga.ui.testing.TestingViewModel
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant
import com.pajaga.utils.other.Constant.TOPIC
import com.pajaga.utils.other.showLogAssert
import com.pajaga.utils.system.moveIntentTo


class NotifService: Service() {

    private var mediaSession: MediaSessionCompat? = null

    override fun onCreate() {
        super.onCreate()
        mediaSession = MediaSessionCompat(this, "PlayerService")
        mediaSession!!.setFlags(
            MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or
                    MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS
        )
        mediaSession!!.setPlaybackState(
            PlaybackStateCompat.Builder()
                .setState(
                    PlaybackStateCompat.STATE_PLAYING,
                    0,
                    0f
                ) //you simulate a player which plays something.
                .build()
        )

        //this will only work on Lollipop and up, see https://code.google.com/p/android/issues/detail?id=224134
        val myVolumeProvider: VolumeProviderCompat = object : VolumeProviderCompat(
            VOLUME_CONTROL_RELATIVE,  /*max volume*/100,  /*initial volume level*/100
        ) {
            override fun onAdjustVolume(direction: Int) {
                if (direction == -1) {
                    val i = SavedData.getInt(Constant.SUM_PLUS)
                    SavedData.setInt(Constant.SUM_PLUS, i.plus(1))
                    showLogAssert("direction", "$i")
                    if (i == 2) {
                        SavedData.setInt(Constant.SUM_PLUS, 0)
                        showLogAssert("token", FirebaseService.token.toString())
                        PushNotification(
                            NotificationData("HELP!!!", "Your friend in a danger situation"),
                            TOPIC
//                            FirebaseService.token!!
                        ).also {
                            val testingViewModel = viewModel as HomeViewModel
                            viewLifecycleOwner?.let { it1 ->
                                testingViewModel.getResponse(it).observe(it1) {
                                    showLogAssert("response", "$it")
                                }
                            }
                        }
//                        if (intent != null && activity != null) {
//                            val intent = intent
//                            activity!!.finish()
//                            startActivity(intent)
//                        }
                    }
                }
                /*
                -1 -- volume down
                1 -- volume up
                0 -- volume button released
                 */
            }
        }
        mediaSession!!.setPlaybackToRemote(myVolumeProvider)
        mediaSession!!.isActive = true
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaSession!!.release()
    }

    companion object {
//        var intent: Intent? = null
//        var activity: ComponentActivity? = null
        var viewModel: ViewModel? = null
        var viewLifecycleOwner: LifecycleOwner? = null
    }
}