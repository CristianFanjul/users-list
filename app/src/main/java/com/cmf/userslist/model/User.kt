package com.cmf.userslist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String,
    val name: String,
    val lastName: String,
    val biography: String,
    val imageUri: String
) : Parcelable
