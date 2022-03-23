package com.pajaga.ui.autentikasi.base

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pajaga.R


@Suppress("DEPRECATION")
class BaseFragment : Fragment(R.layout.fragment_base) {

    private lateinit var viewPagerr : ViewPager
    var exit = false

    private lateinit var bottomNavBar : BottomNavigationView
//    private lateinit var binding :


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        setViewPagerAdapter()
        setBottomNavigation()
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (exit) {
                    activity?.finish()
                    return
                } else {
                    exit = true
                    Handler().postDelayed({ exit = false }, 2000)
                }
            }
        })    }

    private fun setBottomNavigation() {
        bottomNavBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
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