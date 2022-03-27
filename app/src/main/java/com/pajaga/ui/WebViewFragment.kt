package com.pajaga.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import com.pajaga.R
import com.pajaga.databinding.FragmentWebViewBinding
import com.pajaga.utils.system.moveNavigationTo


class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private lateinit var binding: FragmentWebViewBinding
    private var url : String? = ""
    private lateinit var webView: WebView


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onBackPressed()
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebViewBinding.bind(view)
        url = this.arguments?.getString("url")
        webView = binding.webView
        url?.let { webView.loadUrl(it) }
        val webSettings = webView.getSettings()
        webSettings.setJavaScriptEnabled(true)

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