package com.elahe.randomuser.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Registered(
    @ColumnInfo(name = "registeredAge")
    val age: String,
    @ColumnInfo(name = "registeredDate")
    val date: String
) : Parcelable