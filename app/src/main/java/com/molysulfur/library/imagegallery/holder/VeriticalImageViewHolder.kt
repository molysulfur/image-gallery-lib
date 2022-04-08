package com.molysulfur.library.imagegallery.holder

import android.graphics.Bitmap
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.imagegallery.ImageGalleryItem
import com.molysulfur.library.imagegallery.R

class VeriticalImageViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ImageGalleryItem.VeriticalItem, onClick: ((Bitmap?) -> Unit)?) {
        val image = view.findViewById<AppCompatImageView>(R.id.gallery_veritical_image_item)
        with(image) {
            setImageBitmap(item.bitmap)
        }
        view.setOnClickListener {
            onClick?.invoke(item.bitmap)
        }
    }
}