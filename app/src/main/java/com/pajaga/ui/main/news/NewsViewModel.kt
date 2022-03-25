package com.pajaga.ui.main.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pajaga.database.retrofit.RetrofitInstance
import com.pajaga.model.News
import com.pajaga.model.NewsModel
import com.pajaga.model.PushNotification
import com.pajaga.utils.network.Response
import com.pajaga.utils.other.showLogAssert
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NewsViewModel(val rvNews: RecyclerView) : ViewModel() {

    class Factory(val rvNews: RecyclerView) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(rvNews) as T
        }
    }

    val listNews = ArrayList<News>()


    fun setDataNews() {
        listNews.add(News("", "", "30 January 2020", "", ""))
        listNews.add(News("", "", "30 January 2020", "", ""))
        listNews.add(News("", "", "30 January 2020", "", ""))
        listNews.add(News("", "", "30 January 2020", "", ""))
        listNews.add(News("", "", "30 January 2020", "", ""))
        listNews.add(News("", "", "30 January 2020", "", ""))
        listNews.add(News("", "", "30 January 2020", "", ""))
        setRecNews()

    }

    fun setRecNews() {
        val adapterr = NewsAdapter(listNews)
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
                    val response = RetrofitInstance.apiNews.getNews()
//                    showLogAssert("response", response.body().toString())
                    val results = response.body() as NewsModel

                    data.postValue(Response.Changed(results))

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


}