package com.elahe.randomuser.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    val page: String,
    val results: String,
    val seed: String,
    val version: String
):Parcelable