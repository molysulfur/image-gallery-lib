package com.molysulfur.library.imagegallery.holder

import android.graphics.Bitmap
import android.view.View
import android.view.ViewStub
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.imagegallery.ImageGalleryItem
import com.molysulfur.library.imagegallery.R

class RactangleImageViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: ImageGalleryItem.RactangleItem, onClick: ((Bitmap?) -> Unit)?) {
        val image = view.findViewById<AppCompatImageView>(R.id.gallery_image_item)
        with(image) {
            setImageBitmap(item.bitmap)
        }
        view.setOnClickListener {
            onClick?.invoke(item.bitmap)
        }
    }
}