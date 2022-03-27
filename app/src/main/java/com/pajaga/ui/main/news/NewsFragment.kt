package com.pajaga.ui.main.news

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pajaga.R
import com.pajaga.databinding.HomeFragmentBinding
import com.pajaga.databinding.NewsFragmentBinding
import com.pajaga.model.NewNewsModel
import com.pajaga.model.NewsModel
import com.pajaga.utils.network.Response
import com.pajaga.utils.other.showLogAssert
import com.pajaga.utils.system.moveNavigationTo

class NewsFragment : Fragment(R.layout.news_fragment) {

    companion object{
        fun newInstance(): NewsFragment{
            return NewsFragment()
        }
    }

    private lateinit var binding : NewsFragmentBinding


    private val viewModel: NewsViewModel by viewModels {
        NewsViewModel.Factory(binding.rvNews,findNavController())
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()


        binding = NewsFragmentBinding.bind(view)
//        viewModel.setDataNews()
        viewModel.getResponse().observe(viewLifecycleOwner) {
            when(it)  {
                is Response.Changed -> {
                    val data = it.data as NewNewsModel
                    val listData = data.articles
                    showLogAssert("data", "${listData?.size}")
                    listData?.let { it1 -> viewModel.
                    setRecNews(it1) }
                }
                is Response.Error -> showLogAssert("error", it.error)
                is Response.Success -> TODO()
            }
        }
    }

    private fun onBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    moveNavigationTo(requireView(), R.id.action_webViewFragment_to_baseFragment)
                }
            })
    }

}