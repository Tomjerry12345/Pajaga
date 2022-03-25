package com.pajaga.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pajaga.databinding.ItemZoneBinding
import com.pajaga.model.Zone

class ZoneAdapter(val list : ArrayList<Zone>) : RecyclerView.Adapter<ZoneAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding : ItemZoneBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(zoneData : Zone){
            binding.itemZOne = zoneData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemZoneBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.get(position)
        holder.bind(item)    }

    override fun getItemCount(): Int  = list.size



}