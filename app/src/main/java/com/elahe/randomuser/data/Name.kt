package com.elahe.randomuser.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Name(
    val first: String,
    val last: String,
    val title: String
):Parcelable