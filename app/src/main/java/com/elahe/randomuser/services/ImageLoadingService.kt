package com.elahe.randomuser.services

import com.elahe.randomuser.view.CustomImageView

interface ImageLoadingService {
    fun load(imageView: CustomImageView, imageUrl: String)
}