package com.pajaga.ui.base

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pajaga.R
import com.pajaga.databinding.FragmentBaseBinding
import com.pajaga.utils.system.moveNavigationTo


@Suppress("DEPRECATION")
class BaseFragment : Fragment(R.layout.fragment_base) {

    private lateinit var viewPagerr: ViewPager
    var exit = false

    private lateinit var bottomNavBar: BottomNavigationView
    private lateinit var binding: FragmentBaseBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        binding = FragmentBaseBinding.bind(view)
        viewPagerr = binding.fragmentContainer

        bottomNavBar = binding.bottomNavigationView

        setViewPagerAdapter()
        setBottomNavigation()
        viewPagerr = binding.fragmentContainer

        binding.bottomAppBar.setOnClickListener {

        }

        binding.fab.setOnClickListener {
//            showToast(requireContext(), "Terklik")
            moveNavigationTo(it, R.id.action_baseFragment_to_mapsFragment)
        }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (exit) {
                        activity?.finish()
                        return
                    } else {
                        exit = true
                        Handler().postDelayed({ exit = false }, 2000)
                    }
                }
            })
    }

    private fun setBottomNavigation() {
        bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> viewPagerr.currentItem = 0
                R.id.menu_news -> viewPagerr.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun setViewPagerAdapter() {
        viewPagerr.adapter = MainPagerAdapter(childFragmentManager)

    }


}