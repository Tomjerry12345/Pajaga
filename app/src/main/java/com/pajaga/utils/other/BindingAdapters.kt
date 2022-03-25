package com.pajaga.utils.other

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pajaga.R

@BindingAdapter("showImage")
fun showImage(imgView: ImageView, url: Int?){
    Glide.with(imgView.context)
        .load(url)
        .placeholder(R.drawable.orang)
        .into(imgView)
}
@BindingAdapter("showImageUrl")
fun showImageUrl(imgView: ImageView, url: String?){
    Glide.with(imgView.context)
        .load(url)
        .placeholder(R.drawable.orang)
        .into(imgView)
}