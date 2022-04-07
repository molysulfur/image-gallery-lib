package com.molysulfur.library.imagegallery

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImageGalleryLayoutManager(context: Context, adapter: RecyclerView.Adapter<*>, spanCount: Int) :
    GridLayoutManager(context, spanCount) {
    init {
        spanSizeLookup = ImageGalleryLookup(adapter, spanCount)
    }

    override fun canScrollHorizontally(): Boolean = false

    override fun canScrollVertically(): Boolean = false

}
