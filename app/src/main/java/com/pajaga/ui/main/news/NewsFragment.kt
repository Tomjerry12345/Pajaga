package com.pajaga.ui.main.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R

class NewsFragment : Fragment(R.layout.news_fragment) {

    companion object{
        fun newInstance(): NewsFragment{
            return NewsFragment()
        }
    }

    private val viewModel: NewsViewModel by viewModels {
        NewsViewModel.Factory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}