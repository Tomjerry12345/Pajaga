package com.pajaga.ui.examples.firebase.notification

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.database.firebase.FirebaseDatabase
import com.pajaga.databinding.ExamplesFirebaseNotificationFragmentBinding

class ExamplesFirebaseNotificationFragment :
    Fragment(R.layout.examples_firebase_notification_fragment) {

    private val viewModel: ExamplesFirebaseNotificationViewModel by viewModels {
        ExamplesFirebaseNotificationViewModel.Factory(FirebaseDatabase())
    }

    private lateinit var binding: ExamplesFirebaseNotificationFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ExamplesFirebaseNotificationFragmentBinding.bind(view)

    }

}