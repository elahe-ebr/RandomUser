package com.elahe.randomuser.data

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Embedded
    val info: Info,
    @Embedded
    val results: MutableList<Result>
):Parcelable

