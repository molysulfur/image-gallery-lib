package com.molysulfur.library.imagegallery

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


sealed class ImageGalleryItem constructor(val type: Int) : Parcelable {

    @Parcelize
    data class RactangleItem(val bitmap: Bitmap?) : ImageGalleryItem(ImageGalleryType.IMAGE_GALLERY_RECTANGLE)

    @Parcelize
    data class HorizontalItem(val bitmap: Bitmap?) : ImageGalleryItem(ImageGalleryType.IMAGE_GALLERY_HORIZONTAL)

    @Parcelize
    data class VeriticalItem(val bitmap: Bitmap?) : ImageGalleryItem(ImageGalleryType.IMAGE_GALLERY_VERITICAL)

    @Parcelize
    class HorizontalAdapterItem(val images: MutableList<ImageGalleryItem>, val spanCount: Int = 1) : ImageGalleryItem(ImageGalleryType.IMAGE_GALLERY_HORIZONTAL_ADAPTER)

    @Parcelize
    class VeriticalAdapterItem(val images: MutableList<ImageGalleryItem>, val spanCount: Int = 1) : ImageGalleryItem(ImageGalleryType.IMAGE_GALLERY_VERITICAL_ADAPTER)

}