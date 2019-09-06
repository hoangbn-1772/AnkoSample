package com.sun.ankosample.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val userId: String,
    var userName: String = ""
) : Parcelable