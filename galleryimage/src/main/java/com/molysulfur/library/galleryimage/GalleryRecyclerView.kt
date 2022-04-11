package com.molysulfur.library.galleryimage

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class GalleryRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    companion object {
        private val LIMIT_SIZE = 3
    }

    private var urlLists = mutableListOf<Bitmap?>()

    var onItemClick: ((Bitmap?, Int) -> Unit)? = null

    private val wrapperAdapter: ImageGalleryAdapter by lazy {
        ImageGalleryAdapter().apply {
            onClick = clickListener()
        }
    }


    private val galleryAdapter: ImageGalleryAdapter by lazy {
        ImageGalleryAdapter(wrapperAdapter).apply {
            onClick = clickListener()
        }
    }

    private fun clickListener(): ((Bitmap?, Int) -> Unit) = { b, i ->
        onItemClick?.invoke(b, i)
    }

    fun setImageUrlList(urlLists: MutableList<Bitmap?>) {
        this.urlLists = urlLists
        if (adapter == null) {
            adapter = galleryAdapter
        }
        when (this.urlLists.size) {
            1 -> {
                layoutManager = ImageGalleryLayoutManager(context, galleryAdapter, 1)
                galleryAdapter.itemLists = mutableListOf(ImageGalleryItem.HorizontalItem(this.urlLists[0]))
            }
            2 -> {
                val itemList: MutableList<ImageGalleryItem> = convert2PicImage()
                layoutManager = ImageGalleryLayoutManager(context, galleryAdapter, itemList.size)
                galleryAdapter.itemLists = itemList
            }
            3, 4 -> {
                val itemList: MutableList<ImageGalleryItem> = convert3PicImage()
                layoutManager = ImageGalleryLayoutManager(context, galleryAdapter, itemList.size)
                galleryAdapter.itemLists = itemList
            }
            else -> {
                val itemList: MutableList<ImageGalleryItem> = convertMorePicImage()
                layoutManager = ImageGalleryLayoutManager(context, galleryAdapter, itemList.size)
                galleryAdapter.itemLists = itemList
            }
        }
    }

    private fun convertMorePicImage(): MutableList<ImageGalleryItem> {
        val itemList = mutableListOf<ImageGalleryItem>()
        val bitmap = urlLists[0]
        val width = bitmap?.width ?: 0
        val height = bitmap?.height ?: 0
        when {
            width > height -> {
                itemList.add(ImageGalleryItem.HorizontalItem(bitmap))
                val itemList2 = mutableListOf<ImageGalleryItem>()
                for (index in (1 until LIMIT_SIZE)) {
                    val bitmap2 = urlLists[index]
                    itemList2.add(ImageGalleryItem.RactangleItem(bitmap2))
                }
                itemList2.add(ImageGalleryItem.MoreItem(urlLists[4], urlLists.size.minus(LIMIT_SIZE)))
                itemList.add(ImageGalleryItem.HorizontalAdapterItem(images = itemList2, spanCount = 3))
            }
            width == height -> {}
            width < height -> {
                itemList.add(ImageGalleryItem.VeriticalItem(bitmap))
                val itemList2 = mutableListOf<ImageGalleryItem>()
                for (index in (1 until LIMIT_SIZE)) {
                    val bitmap2 = urlLists[index]
                    itemList2.add(ImageGalleryItem.RactangleItem(bitmap2))
                }
                itemList2.add(ImageGalleryItem.MoreItem(urlLists[4], urlLists.size.minus(LIMIT_SIZE)))
                itemList.add(ImageGalleryItem.VeriticalAdapterItem(images = itemList2, spanCount = 1))
            }
        }
        return itemList
    }

    private fun convert3PicImage(): MutableList<ImageGalleryItem> {
        val itemList = mutableListOf<ImageGalleryItem>()
        val bitmap = urlLists[0]
        val width = bitmap?.width ?: 0
        val height = bitmap?.height ?: 0
        when {
            width > height -> {
                itemList.add(ImageGalleryItem.HorizontalItem(bitmap))
                val itemList2 = mutableListOf<ImageGalleryItem>()
                for (index in (1 until urlLists.size)) {
                    val bitmap2 = urlLists[index]
                    itemList2.add(ImageGalleryItem.RactangleItem(bitmap2))
                }
                itemList.add(ImageGalleryItem.HorizontalAdapterItem(images = itemList2, spanCount = 3))
            }
            width == height -> {}
            width < height -> {
                itemList.add(ImageGalleryItem.VeriticalItem(bitmap))
                val itemList2 = mutableListOf<ImageGalleryItem>()
                for (index in (1 until urlLists.size)) {
                    val bitmap2 = urlLists[index]
                    itemList2.add(ImageGalleryItem.RactangleItem(bitmap2))
                }
                itemList.add(ImageGalleryItem.VeriticalAdapterItem(images = itemList2, spanCount = 1))
            }
        }
        return itemList
    }

    private fun convert2PicImage(): MutableList<ImageGalleryItem> {
        val itemList = mutableListOf<ImageGalleryItem>()
        val bitmap = urlLists[0]
        val width = bitmap?.width ?: 0
        val height = bitmap?.height ?: 0
        when {
            width > height -> {
                repeat(urlLists.size) {
                    itemList.add(ImageGalleryItem.HorizontalItem(urlLists[it]))
                }
            }
            width == height -> {}
            width < height -> {
                repeat(urlLists.size) {
                    itemList.add(ImageGalleryItem.VeriticalItem(urlLists[it]))
                }
            }
        }
        return itemList
    }
}