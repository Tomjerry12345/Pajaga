package com.pajaga.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pajaga.ui.main.home.HomeFragment
import com.pajaga.ui.main.news.NewsFragment

@Suppress("DEPRECATION")
class MainPagerAdapter(var fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return  2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment.newInstance()
            1 -> NewsFragment.newInstance()
            else -> HomeFragment.newInstance()
        }
    }

}