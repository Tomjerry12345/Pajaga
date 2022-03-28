package com.pajaga.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.databinding.ItemPermissionBinding
import com.pajaga.model.Permission


class PermissionAdapter(val list : ArrayList<Permission>) : RecyclerView.Adapter<PermissionAdapter.ViewHolder>(){

    inner class ViewHolder(private var binding : ItemPermissionBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(permissionData : Permission){
            binding.itemPermission = permissionData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemPermissionBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)    }

    override fun getItemCount(): Int  = list.size

}