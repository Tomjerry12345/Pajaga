package com.pajaga.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pajaga.R
import com.pajaga.service.handphone.NotifService
import com.pajaga.utils.local.SavedData

class TestingActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

        SavedData.init(this)

//        PlayerService.intent = intent
//        PlayerService.activity = this

        startService(Intent(this, NotifService::class.java))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        return if (SavedData.getBoolean(Constant.ONACTIVED_KEY_VOLUME)) {
//            when (keyCode) {
//                KeyEvent.KEYCODE_VOLUME_DOWN -> {
//                    val i = SavedData.getInt(Constant.SUM_PLUS)
//                    SavedData.setInt(Constant.SUM_PLUS, i.plus(1))
////                    showLogAssert("i", "$i")
////                    showLogAssert("clicked", "KEYCODE_VOLUME_DOWN")
//                    if (i == 2) {
//                        moveIntentTo(this, TestingActivity())
//                    }
//                    true
//                }
//                KeyEvent.KEYCODE_VOLUME_UP -> {
//                    showLogAssert("clicked", "KEYCODE_VOLUME_UP")
//                    true
//                }
//                else -> super.onKeyDown(keyCode, event)
//            }
//        } else {
//            false
//        }
//
//    }
}