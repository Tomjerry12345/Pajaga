package com.pajaga.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.databinding.ItemContactBinding
import com.pajaga.model.Contact

class ContactAdapter(val list: ArrayList<Contact>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){

    inner class ViewHolder(private var binding : ItemContactBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(contactData : Contact){
            binding.itemComic = contactData
            binding.executePendingBindings()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemContactBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)

    }

    override fun getItemCount(): Int  = list.size
}