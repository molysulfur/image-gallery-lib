package com.molysulfur.library.imagegallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.launch

/**
 * https://picsum.photos/seed/{seed}/{width}/{height}
 */
class MainActivity : AppCompatActivity() {

    private val urlLists = mutableListOf(
        "https://picsum.photos/seed/1/1000/500",
        "https://picsum.photos/seed/2/1000/500",
        "https://picsum.photos/seed/3/1000/500",
        "https://picsum.photos/seed/4/1000/500"
    )

    private val wrapperAdapter: ImageGalleryAdapter by lazy {
        ImageGalleryAdapter()
    }

    private val galleryAdapter: ImageGalleryAdapter by lazy {
        ImageGalleryAdapter(wrapperAdapter)
    }
    private val imageLoader: ImageLoader by lazy {
        ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                when (urlLists.size) {
                    2 -> {
                        val itemList: MutableList<ImageGalleryItem> = convert2PicImage()
                        with(recycler) {
                            layoutManager = ImageGalleryLayoutManager(context, galleryAdapter, itemList.size)
                            adapter = galleryAdapter
                        }
                        galleryAdapter.itemLists = itemList
                    }
                    3, 4 -> {
                        val itemList: MutableList<ImageGalleryItem> = convert3PicImage()
                        with(recycler) {
                            layoutManager = ImageGalleryLayoutManager(context, galleryAdapter, itemList.size)
                            adapter = galleryAdapter
                        }
                        galleryAdapter.itemLists = itemList

                    }
                }
            }
        }
    }

    private suspend fun convert3PicImage(): MutableList<ImageGalleryItem> {
        val itemList = mutableListOf<ImageGalleryItem>()
        val image1 = urlLists[0]
        val request = ImageRequest.Builder(context = this@MainActivity)
            .data(image1)
            .build()
        val bitmap = imageLoader.execute(request).drawable?.toBitmap()
        val width = bitmap?.width ?: 0
        val height = bitmap?.height ?: 0
        when {
            width > height -> {
                itemList.add(ImageGalleryItem.HorizontalItem(bitmap))
                val itemList2 = mutableListOf<ImageGalleryItem>()
                for (index in (1 until urlLists.size)) {
                    val request2 = ImageRequest.Builder(context = this@MainActivity)
                        .data(urlLists[index])
                        .build()
                    val bitmap2 = imageLoader.execute(request2).drawable?.toBitmap()
                    itemList2.add(ImageGalleryItem.RactangleItem(bitmap2))
                }
                itemList.add(ImageGalleryItem.HorizontalAdapterItem(images = itemList2, spanCount = 3))
            }
            width == height -> {}
            width < height -> {
                itemList.add(ImageGalleryItem.VeriticalItem(bitmap))
                val itemList2 = mutableListOf<ImageGalleryItem>()
                for (index in (1 until urlLists.size)) {
                    val request2 = ImageRequest.Builder(context = this@MainActivity)
                        .data(urlLists[index])
                        .build()
                    val bitmap2 = imageLoader.execute(request2).drawable?.toBitmap()
                    itemList2.add(ImageGalleryItem.RactangleItem(bitmap2))
                }
                itemList.add(ImageGalleryItem.VeriticalAdapterItem(images = itemList2, spanCount = 1))
            }
        }
        return itemList
    }

    private suspend fun convert2PicImage(): MutableList<ImageGalleryItem> {
        val itemList = mutableListOf<ImageGalleryItem>()
        val image1 = urlLists[0]
        val request = ImageRequest.Builder(context = this@MainActivity)
            .data(image1)
            .build()
        val bitmap = imageLoader.execute(request).drawable?.toBitmap()
        val width = bitmap?.width ?: 0
        val height = bitmap?.height ?: 0
        when {
            width > height -> {
                repeat(urlLists.size) {
                    val request = ImageRequest.Builder(context = this@MainActivity)
                        .data(image1)
                        .build()
                    val bitmap = imageLoader.execute(request).drawable?.toBitmap()
                    itemList.add(ImageGalleryItem.HorizontalItem(bitmap))
                }
            }
            width == height -> {}
            width < height -> {
                repeat(urlLists.size) {
                    val request = ImageRequest.Builder(context = this@MainActivity)
                        .data(image1)
                        .build()
                    val bitmap = imageLoader.execute(request).drawable?.toBitmap()
                    itemList.add(ImageGalleryItem.VeriticalItem(bitmap))
                }
            }
        }
        return itemList
    }
}