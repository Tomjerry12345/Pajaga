package com.pajaga.ui.main.profil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.pajaga.R
import com.pajaga.model.Contact
import com.pajaga.ui.main.home.adapter.ContactAdapter
import com.pajaga.ui.main.home.adapter.ContactDangerAdapter

class ProfilActivity : AppCompatActivity() {
    val listContact = ArrayList<Contact>()
    private lateinit var rvZone: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        rvZone = findViewById(R.id.rv_danger)
        setData()

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

    fun setData() {
        listContact.add(Contact(R.drawable.orang, "Surya", "Brother"))
        listContact.add(Contact(R.drawable.orang1, "Dani", "Friend"))
        listContact.add(Contact(R.drawable.orang3, "Andri", "College", "", false))
        setRecContact()

    }

    fun setRecContact() {

        val adapterr = ContactDangerAdapter(listContact)
        rvZone.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterr
        }

    }
}