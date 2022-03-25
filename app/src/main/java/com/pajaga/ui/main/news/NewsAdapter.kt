package com.pajaga.ui.main.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.databinding.ItemNewsBinding
import com.pajaga.model.News

class NewsAdapter(val list : ArrayList<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding : ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(newsData : News){
            binding.itemNews = newsData
            binding.executePendingBindings()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemNewsBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)    }

    override fun getItemCount(): Int  = list.size

}