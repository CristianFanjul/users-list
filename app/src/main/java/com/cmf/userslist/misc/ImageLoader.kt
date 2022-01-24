package com.cmf.userslist.misc

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageLoader {
    fun loadImage(thumbnail: String, view: ImageView) {
        Glide.with(view.context)
            .load(thumbnail)
            .centerCrop()
            .into(view)
    }
}
