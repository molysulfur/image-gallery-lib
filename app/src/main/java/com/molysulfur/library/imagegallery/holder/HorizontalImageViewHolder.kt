package com.molysulfur.library.imagegallery.holder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.imagegallery.ImageGalleryItem
import com.molysulfur.library.imagegallery.R

class HorizontalImageViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ImageGalleryItem.HorizontalItem) {
        val image = view.findViewById<AppCompatImageView>(R.id.gallery_horizontal_image_item)
        with(image) {
            setImageBitmap(item.bitmap)
        }
    }
}