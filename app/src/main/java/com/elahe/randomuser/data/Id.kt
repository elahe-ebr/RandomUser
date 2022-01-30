package com.elahe.randomuser.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Id(
    @ColumnInfo(name="idName")
    val name: String,
    val value: String
):Parcelable