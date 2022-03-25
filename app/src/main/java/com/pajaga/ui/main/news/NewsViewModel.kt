package com.pajaga.ui.main.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.model.Contact
import com.pajaga.model.News
import com.pajaga.ui.main.home.ContactAdapter

class NewsViewModel(val rvNews : RecyclerView) : ViewModel() {

    class Factory(val rvNews : RecyclerView) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(rvNews) as T
        }
    }
    val listNews = ArrayList<News>()


    fun setDataNews(){
        listNews.add(News("","","30 January 2020","",""))
        listNews.add(News("","","30 January 2020","",""))
        listNews.add(News("","","30 January 2020","",""))
        listNews.add(News("","","30 January 2020","",""))
        listNews.add(News("","","30 January 2020","",""))
        listNews.add(News("","","30 January 2020","",""))
        listNews.add(News("","","30 January 2020","",""))
        setRecNews()

    }

    fun setRecNews(){
        val adapterr = NewsAdapter(listNews)
        rvNews.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = adapterr
        }

    }




}