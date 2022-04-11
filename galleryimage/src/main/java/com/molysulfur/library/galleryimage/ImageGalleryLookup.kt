package com.molysulfur.library.galleryimage

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImageGalleryLookup(private val adapter: RecyclerView.Adapter<*>, private val columnCount: Int) :
    GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int = when (adapter.getItemViewType(position)) {
        ImageGalleryType.IMAGE_GALLERY_RECTANGLE -> 1
        ImageGalleryType.IMAGE_GALLERY_HORIZONTAL -> columnCount
        ImageGalleryType.IMAGE_GALLERY_VERITICAL -> 1
        ImageGalleryType.IMAGE_GALLERY_HORIZONTAL_ADAPTER -> columnCount
        ImageGalleryType.IMAGE_GALLERY_VERITICAL_ADAPTER -> 1
        else -> 1
    }

}