package com.molysulfur.library.imagegallery

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter constructor(private val bitmaps: MutableList<Bitmap?>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder = MainViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_example_gallery_view, parent, false)
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(bitmaps)
    }

    override fun getItemCount(): Int = 1000


    class MainViewHolder constructor(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(bitmaps: MutableList<Bitmap?>) {
            val gallery = view.findViewById<GalleryRecyclerView>(R.id.gallery)
            gallery.setImageUrlList(bitmaps)
            gallery.onItemClick = { bitmap, position ->
                Log.e("Item $position", "$position,$bitmap")
            }
        }
    }
}