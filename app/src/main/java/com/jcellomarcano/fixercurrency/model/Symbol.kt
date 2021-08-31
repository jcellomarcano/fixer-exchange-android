package com.jcellomarcano.fixercurrency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Symbol(
    val symbol: String,
    val name: String
) : Parcelable