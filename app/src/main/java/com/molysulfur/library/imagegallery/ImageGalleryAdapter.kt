package com.molysulfur.library.imagegallery

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.molysulfur.library.imagegallery.holder.*
import java.lang.Error

class ImageGalleryAdapter constructor(private val wrapperAdapter: ImageGalleryAdapter? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemLists = mutableListOf<ImageGalleryItem>()
        set(value) {
            Log.e("ImageGalleryAdapter", "$value")
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        ImageGalleryType.IMAGE_GALLERY_RECTANGLE ->
            RactangleImageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.image_gallery_item_ractangle, parent, false)
            )
        ImageGalleryType.IMAGE_GALLERY_HORIZONTAL ->
            HorizontalImageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.image_gallery_item_horizontal, parent, false)
            )
        ImageGalleryType.IMAGE_GALLERY_VERITICAL ->
            VeriticalImageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.image_gallery_item_veritical, parent, false)
            )
        ImageGalleryType.IMAGE_GALLERY_HORIZONTAL_ADAPTER -> HorizontalAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_gallery_item_recycler, parent, false)
        )
        ImageGalleryType.IMAGE_GALLERY_VERITICAL_ADAPTER -> VeriticalAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_gallery_item_recycler, parent, false)
        )
        ImageGalleryType.IMAGE_GALLERY_MORE -> MoreImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_gallery_item_more, parent, false)
        )
        else -> throw Error("Image Gallery isn't match type.")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemLists[position]
        when (holder) {
            is MoreImageViewHolder -> holder.bind(item = item as ImageGalleryItem.MoreItem)
            is RactangleImageViewHolder -> holder.bind(item = item as ImageGalleryItem.RactangleItem)
            is HorizontalImageViewHolder -> holder.bind(item = item as ImageGalleryItem.HorizontalItem)
            is VeriticalImageViewHolder -> holder.bind(item = item as ImageGalleryItem.VeriticalItem)
            is HorizontalAdapterViewHolder -> holder.bind(imageAdapter = wrapperAdapter, item = item as ImageGalleryItem.HorizontalAdapterItem)
            is VeriticalAdapterViewHolder -> holder.bind(imageAdapter = wrapperAdapter, item = item as ImageGalleryItem.VeriticalAdapterItem)
        }
    }

    override fun getItemViewType(position: Int): Int = itemLists[position].type

    override fun getItemCount(): Int = itemLists.size

}