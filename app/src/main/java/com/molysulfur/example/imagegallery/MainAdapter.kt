package com.molysulfur.example.imagegallery

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.galleryimage.GalleryRecyclerView
import com.molysulfur.library.imagegallery.R

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
            gallery.onItemClick = { _, position ->
                Toast.makeText(view.context, "Item $position", Toast.LENGTH_SHORT).show()
            }
        }
    }
}