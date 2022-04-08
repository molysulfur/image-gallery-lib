package com.molysulfur.library.imagegallery.holder

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.imagegallery.ImageGalleryItem
import com.molysulfur.library.imagegallery.R

class MoreImageViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: ImageGalleryItem.MoreItem, onClick: ((Bitmap?) -> Unit)?) {
        val image = view.findViewById<ImageView>(R.id.gallery_image_more_image_preview)
        val text = view.findViewById<AppCompatTextView>(R.id.gallery_image_more_text_count)
        with(image) {
            setImageBitmap(item.bitmap)
        }
        with(text) {
            setText("+%s".format(item.count))
        }
        view.setOnClickListener {
            onClick?.invoke(item.bitmap)
        }
    }
}