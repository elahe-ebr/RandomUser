package com.elahe.randomuser.services

import com.elahe.randomuser.R
import com.elahe.randomuser.view.CustomImageView
import com.facebook.drawee.view.SimpleDraweeView

class FrescoImageLoadingService : ImageLoadingService {
    override fun load(imageView: CustomImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView) {
            imageView.setImageURI(imageUrl)
            imageView.hierarchy.setPlaceholderImage(R.drawable.ic_user_place_holder)
        } else
            throw IllegalAccessException("ImageView must be instance of simpleDraweeView")
    }
}