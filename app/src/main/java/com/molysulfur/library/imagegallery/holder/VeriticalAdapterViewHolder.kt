package com.molysulfur.library.imagegallery.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.imagegallery.ImageGalleryAdapter
import com.molysulfur.library.imagegallery.ImageGalleryItem
import com.molysulfur.library.imagegallery.ImageGalleryLayoutManager
import com.molysulfur.library.imagegallery.R

class VeriticalAdapterViewHolder constructor(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(imageAdapter: ImageGalleryAdapter?, item: ImageGalleryItem.VeriticalAdapterItem) {
        if (imageAdapter != null) {
            val recycler = view.findViewById<RecyclerView>(R.id.gallery_image_recycler_item)
            with(recycler) {
                layoutManager = ImageGalleryLayoutManager(view.context, imageAdapter, item.spanCount)
                adapter = imageAdapter
            }
            imageAdapter.itemLists = item.images
        }
    }
}