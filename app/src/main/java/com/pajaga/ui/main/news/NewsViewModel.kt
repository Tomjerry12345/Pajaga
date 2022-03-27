package com.pajaga.ui.main.news

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pajaga.R
import com.pajaga.database.retrofit.RetrofitInstance
import com.pajaga.model.*
import com.pajaga.ui.WebViewFragment
import com.pajaga.utils.network.Response
import com.pajaga.utils.other.showLogAssert
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NewsViewModel(val rvNews: RecyclerView,val navController: NavController) : ViewModel() {

    class Factory(val rvNews: RecyclerView,val navController: NavController) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(rvNews, navController) as T
        }
    }

    val listNews = ArrayList<News>()




    fun setRecNews(list : ArrayList<Articles>) {
        val adapterr = NewsAdapter(list,{item : String -> onClickItem(item)})
        rvNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterr
        }

    }

    fun getResponse(): MutableLiveData<Response> {
        val data = MutableLiveData<Response>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = RetrofitInstance.apiNewsAPI.getNews()
                    val results = response.body() as NewNewsModel

                    data.postValue(Response.Changed(results))

                    Log.d("news", "getResponse: $listNews")
                    Log.d("news", "getResponse: $data")


                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> {
                            data.postValue(Response.Error("Network Error => ${throwable.message}"))
                        }
                        is HttpException -> {
                            val code = throwable.code()
                            val errorResponse = throwable.message()
                            data.postValue(Response.Error("Error $code $errorResponse"))
                        }
                        else -> {
                            data.postValue(Response.Error("Unknown Error => ${throwable.message}"))
                        }
                    }
                }
            }
        }

        return data
    }


    private fun onClickItem(url: String){

        val bundle = Bundle()
        val webViewFragment = WebViewFragment()
        bundle.putString("url",url)
        webViewFragment.arguments = bundle
        navController.navigate(
            R.id.webViewFragment,bundle
        )

    }


}