package com.pajaga.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.messaging.FirebaseMessaging
import com.pajaga.R
import com.pajaga.databinding.HomeFragmentBinding
import com.pajaga.service.firebase.FirebaseService
import com.pajaga.service.handphone.PlayerService
import com.pajaga.ui.main.MainActivity
import com.pajaga.ui.testing.TOPIC
import com.pajaga.utils.local.SavedData
import com.pajaga.utils.other.Constant
import com.pajaga.utils.other.showLogAssert
import com.pajaga.utils.other.showToast
import com.pajaga.utils.system.moveIntentTo

class HomeFragment : Fragment(R.layout.home_fragment) {

    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory(binding.rvContact,binding.rvZone)
    }

    private lateinit var binding : HomeFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HomeFragmentBinding.bind(view)
        viewModel.setData()
        viewModel.setDataZone()

        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            FirebaseService.token = it
//            showToast(requireContext(), it)
//            showLogAssert("token", it)
//            etToken.setText(it.token)
//            getPermission()
        }

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        PlayerService.viewModel = viewModel
        PlayerService.viewLifecycleOwner = viewLifecycleOwner

        binding.switchNotif.isChecked = SavedData.getBoolean(Constant.ONACTIVED_KEY_VOLUME)

        binding.switchNotif.setOnCheckedChangeListener { compoundButton, checked ->
            SavedData.setBoolean(Constant.ONACTIVED_KEY_VOLUME, checked)
            moveIntentTo(requireActivity(), MainActivity())
        }

    }

    override fun onResume() {
        super.onResume()
        showLogAssert("onResume", "true")
    }

    override fun onPause() {
        super.onPause()
        showLogAssert("onPause", "true")
//        moveIntentTo(requireActivity(), MainActivity())
    }

}