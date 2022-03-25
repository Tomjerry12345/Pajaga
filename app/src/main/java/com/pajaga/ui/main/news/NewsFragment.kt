package com.pajaga.ui.main.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pajaga.R
import com.pajaga.databinding.HomeFragmentBinding
import com.pajaga.databinding.NewsFragmentBinding
import com.pajaga.model.NewsModel
import com.pajaga.utils.network.Response
import com.pajaga.utils.other.showLogAssert

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
//        viewModel.setDataNews()
        viewModel.getResponse().observe(viewLifecycleOwner) {
            when(it)  {
                is Response.Changed -> {
                    val data = it.data as NewsModel
                    val listData = data.results
                    showLogAssert("data", "${listData?.size}")
                    listData?.let { it1 -> viewModel.setRecNews(it1) }
                }
                is Response.Error -> showLogAssert("error", it.error)
                is Response.Success -> TODO()
            }
        }
    }

}