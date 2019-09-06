package com.sun.ankosample.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(
    val username: String = "",
    var password: String = ""
) : Parcelable
