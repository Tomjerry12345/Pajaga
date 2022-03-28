package com.pajaga.utils.other

import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("showFirstChar")
fun showFirstChar(textView: TextView, url: String?){
    textView.setText(url?.get(0).toString())
}

@BindingAdapter("visibilityLine")
fun visibilityLine(view: View, url: Boolean?){
    if (url == false) View.GONE.also { view.visibility = it } else view.visibility = View.VISIBLE
}