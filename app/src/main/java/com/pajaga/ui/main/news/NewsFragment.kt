package com.pajaga.ui.main.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.databinding.HomeFragmentBinding
import com.pajaga.databinding.NewsFragmentBinding

class NewsFragment : Fragment(R.layout.news_fragment) {

    companion object{
        fun newInstance(): NewsFragment{
            return NewsFragment()
        }
    }

    private lateinit var binding : NewsFragmentBinding


    private val viewModel: NewsViewModel by viewModels {
        NewsViewModel.Factory(binding.rvNews)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = NewsFragmentBinding.bind(view)
        viewModel.setDataNews()
    }

}