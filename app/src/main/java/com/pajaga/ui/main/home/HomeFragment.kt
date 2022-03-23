package com.pajaga.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.databinding.HomeFragmentBinding
import com.pajaga.ui.main.MainActivity
import com.pajaga.utils.system.moveIntentTo

class HomeFragment : Fragment(R.layout.home_fragment) {

    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Factory(binding.rvContact)
    }

    private lateinit var binding : HomeFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = HomeFragmentBinding.bind(view)
        viewModel.setData()

    }

}