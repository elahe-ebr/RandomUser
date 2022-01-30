package com.elahe.randomuser.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tbl_user")
@Parcelize
data class Result(
    val cell: String,
    @Embedded
    val dob: Dob,
    val email: String,
    val gender: String,
    @Embedded
    val id: Id,
    @Embedded
    val location: Location,
    @PrimaryKey
    @Embedded
    val login: Login,
    @Embedded
    val name: Name,
    val nat: String,
    val phone: String,
    @Embedded
    val picture: Picture,
    @Embedded
    val registered: Registered
) : Parcelable