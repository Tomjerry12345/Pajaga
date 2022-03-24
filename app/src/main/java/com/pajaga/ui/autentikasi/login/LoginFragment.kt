package com.pajaga.ui.autentikasi.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.database.firebase.FirebaseDatabase
import com.pajaga.databinding.LoginFragmentBinding

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModel.Factory(FirebaseDatabase(), requireActivity())
    }

    private lateinit var binding: LoginFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = LoginFragmentBinding.bind(view)
        binding.viewModel = viewModel

        moveToHome()
    }

    fun moveToHome() {
        binding.mbLogin.setOnClickListener {
            viewModel.onMoveToHome(binding, it)
        }
    }

}