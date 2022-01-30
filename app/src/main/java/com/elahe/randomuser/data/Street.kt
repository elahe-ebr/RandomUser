package com.elahe.randomuser.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Street(
    val name: String,
    val number: String
):Parcelable