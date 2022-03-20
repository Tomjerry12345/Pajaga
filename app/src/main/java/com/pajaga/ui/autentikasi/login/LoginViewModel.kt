package com.pajaga.ui.autentikasi.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pajaga.database.firebase.FirebaseDatabase

class LoginViewModel(val firebaseDatabase: FirebaseDatabase) : ViewModel() {

    class Factory(private val firebaseDatabase: FirebaseDatabase) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(firebaseDatabase) as T
        }
    }

}