package com.arttseng.cathaybk.tools

import android.widget.ImageView
import com.arttseng.cathaybk.MyApplication
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(url: String?) {
    Glide.with(MyApplication.instance())
        .load(url)
        .into(this)
}

fun ImageView.roundImage(url: String?) {
    Glide.with(MyApplication.instance())
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

