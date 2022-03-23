package com.pajaga.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.R
import com.pajaga.model.Contact

class HomeViewModel(val rvContact : RecyclerView) : ViewModel() {

    class Factory(val rvContact : RecyclerView) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(rvContact) as T
        }
    }

    val listContact = ArrayList<Contact>()

    fun setData(){
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        listContact.add(Contact(R.drawable.orang,"Surya","Brother"))
        setRecContact()

    }

    fun setRecContact(){

        val adapterr = ContactAdapter(listContact)
        rvContact.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = adapterr
        }



    }



}