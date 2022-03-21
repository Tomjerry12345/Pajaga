package com.pajaga.ui.autentikasi.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.database.firebase.FirebaseDatabase

class RegisterFragment : Fragment(R.layout.register_fragment) {

    private val viewModel: RegisterViewModel by viewModels {
        RegisterViewModel.Factory(FirebaseDatabase())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}