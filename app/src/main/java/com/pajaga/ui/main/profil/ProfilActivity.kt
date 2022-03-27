package com.pajaga.ui.main.profil

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.pajaga.R

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val mbProfil = findViewById<MaterialButton>(R.id.maps)
        mbProfil.setOnClickListener {
            moveToMaps()
        }
    }

    private fun moveToMaps() {
        val testLatitude = "-5.2057715"
        val testLongitude = "119.4951314"
        val gmmIntentUri = Uri.parse("google.navigation:q=$testLatitude,$testLongitude")

        val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}