package com.pajaga.ui.testing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.messaging.FirebaseMessaging
import com.pajaga.R
import com.pajaga.database.retrofit.RetrofitInstance
import com.pajaga.model.NotificationData
import com.pajaga.model.PushNotification
import com.pajaga.service.firebase.FirebaseService
import com.pajaga.utils.other.showLogAssert

const val TOPIC = "/topics/testing"

class TestingFragment : Fragment(R.layout.testing_fragment) {

    private val viewModel: TestingViewModel by viewModels {
        TestingViewModel.Factory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
//            showToast(requireContext(), it)
            showLogAssert("token", it)
//            etToken.setText(it.token)
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        PushNotification(
            NotificationData("test", "test message"),
            FirebaseService.token!!
        ).also {
            viewModel.getResponse(it).observe(viewLifecycleOwner) {
                showLogAssert("response", "$it")
            }
        }

    }

//    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val response = RetrofitInstance.api.postNotification(notification)
//            if(response.isSuccessful) {
//                showLogAssert("response", "Response: ${Gson().toJson(response)}")
//            } else {
//                showLogAssert("error", response.errorBody().toString())
//            }
//        } catch(e: Exception) {
//            showLogAssert("exception", e.toString())
//        }
//    }

}