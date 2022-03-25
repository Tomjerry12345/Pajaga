package com.pajaga.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.pajaga.R
import com.pajaga.service.handphone.NotifService
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant
import com.pajaga.utils.other.showLogAssert

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SavedData.init(this)

        val checked = SavedData.getBoolean(Constant.ONACTIVED_KEY_VOLUME)

        showLogAssert("checked", "$checked")

        if (checked) {
            startService(Intent(this, NotifService::class.java))
        } else {
            stopService(Intent(this, NotifService::class.java))
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}