package com.elahe.randomuser.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Timezone(
    val description: String,
    val offset: String
):Parcelable