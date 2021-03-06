package com.jcellomarcano.fixercurrency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.StringBufferInputStream

@Parcelize
class Currency(
    val symbol: String,
    val exchangeRate: String,
    val date: String,
) : Parcelable