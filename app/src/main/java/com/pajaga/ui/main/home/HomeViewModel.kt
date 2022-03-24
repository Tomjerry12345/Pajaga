package com.pajaga.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.R
import com.pajaga.model.Contact
import com.pajaga.model.Zone

class HomeViewModel(val rvContact : RecyclerView, val rvZone: RecyclerView) : ViewModel() {

    class Factory(val rvContact : RecyclerView, val rvZone : RecyclerView) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(rvContact,rvZone) as T
        }
    }

    val listContact = ArrayList<Contact>()
    val listZone = ArrayList<Zone>()

    fun setData(){
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        setRecContact()

    }

    fun setDataZone(){
        listZone.add(Zone("Jl. Sultan Hasanuddin","23:00 - 04:00 Wita"))
        listZone.add(Zone("Jl. Sultan Hasanuddin","23:00 - 04:00 Wita"))
        listZone.add(Zone("Jl. Sultan Hasanuddin","23:00 - 04:00 Wita"))
        setRecZone()

    }

    fun setRecContact(){

        val adapterr = ContactAdapter(listContact)
        rvContact.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = adapterr
        }

    }

    fun setRecZone(){
        val adapterrr = ZoneAdapter(listZone)
        rvZone.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = adapterrr
        }

    }



}