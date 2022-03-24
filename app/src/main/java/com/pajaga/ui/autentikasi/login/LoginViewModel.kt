package com.pajaga.ui.autentikasi.login

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pajaga.R
import com.pajaga.database.firebase.FirebaseDatabase
import com.pajaga.databinding.LoginFragmentBinding
import com.pajaga.ui.main.MainActivity
import com.pajaga.utils.other.showToast
import com.pajaga.utils.system.moveIntentTo
import com.pajaga.utils.system.moveNavigationTo

@SuppressLint("StaticFieldLeak")
class LoginViewModel(val firebaseDatabase: FirebaseDatabase, val activity: FragmentActivity) :
    ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun onMoveToHome(binding: LoginFragmentBinding, view: View) {
        try {
            val email = email.value ?: throw Exception("Email tidak boleh kosong")
            val password = password.value ?: throw Exception("Password tidak boleh kosong")
            binding.progressDialog.visibility = View.VISIBLE
            val timer = object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    if (email == "test@gmail.com" && password == "55555") {
                        moveIntentTo(activity, MainActivity())
                        activity.finish()
                    } else {
                        binding.progressDialog.visibility = View.GONE
                        showToast(view.context, "Email Atau Password salah")
                    }

                }
            }
            timer.start()


        } catch (e: Exception) {
            binding.progressDialog.visibility = View.GONE
            showToast(view.context, "${e.message}")
        }
    }

    fun onMoveToRegister(view: View) {
        moveNavigationTo(view, R.id.action_loginFragment_to_registerFragment)
    }

    class Factory(
        private val firebaseDatabase: FirebaseDatabase,
        val activity: FragmentActivity
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(firebaseDatabase, activity) as T
        }
    }

}