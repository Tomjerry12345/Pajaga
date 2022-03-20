package com.pajaga.ui.examples.firebase

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pajaga.R
import com.pajaga.utils.other.showToast
import com.pajaga.utils.system.moveNavigationTo

class ExamplesFirebaseViewModel : ViewModel() {

    fun onMoveToCrud(view: View) {
        showToast(view.context, "Belum tersedia")
    }

    fun onMoveToNotification(view: View) {
        moveNavigationTo(view, R.id.action_examplesFirebaseFragment_to_examplesFirebaseNotificationFragment)
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExamplesFirebaseViewModel() as T
        }
    }

}