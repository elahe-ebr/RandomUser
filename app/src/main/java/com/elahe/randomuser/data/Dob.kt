package com.elahe.randomuser.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dob(
    val age: String,
    val date: String
):Parcelable