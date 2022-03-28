package com.pajaga.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.databinding.ItemPermissionBinding
import com.pajaga.databinding.ItemProfileActivityBinding
import com.pajaga.model.Contact
import com.pajaga.model.Permission

class ContactDangerAdapter(val list : ArrayList<Contact>) : RecyclerView.Adapter<ContactDangerAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding : ItemProfileActivityBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(permissionData : Contact){
            binding.itemContact = permissionData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemProfileActivityBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)       }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)      }

    override fun getItemCount(): Int  = list.size


}