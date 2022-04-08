package com.molysulfur.library.imagegallery

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.launch

/**
 * https://picsum.photos/seed/{seed}/{width}/{height}
 */
class MainActivity : AppCompatActivity() {

    private val urlLists = mutableListOf(
        "https://source.unsplash.com/random/1000x500",
        "https://source.unsplash.com/random/1000x500",
        "https://source.unsplash.com/random/1000x500",
        "https://source.unsplash.com/random/1000x500",
        "https://source.unsplash.com/random/1000x500"
    )

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val imageLoader = ImageLoader.Builder(this)
            .crossfade(true)
            .build()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val bitmaps: List<Bitmap?> = urlLists.map {
                    val request = ImageRequest.Builder(context = this@MainActivity)
                        .data(it)
                        .build()
                    imageLoader.execute(request).drawable?.toBitmap()
                }
                with(recycler) {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    adapter = MainAdapter(bitmaps = bitmaps.toMutableList())
                }
            }
        }


    }

}