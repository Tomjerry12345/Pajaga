package com.pajaga.ui.examples.firebase.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pajaga.database.firebase.FirebaseDatabase

class ExamplesFirebaseNotificationViewModel(firebaseDatabase: FirebaseDatabase) : ViewModel() {

    class Factory(private val firebaseDatabase: FirebaseDatabase) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExamplesFirebaseNotificationViewModel(firebaseDatabase) as T
        }
    }

}