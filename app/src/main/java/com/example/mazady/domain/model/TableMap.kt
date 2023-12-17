package com.example.mazady.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TableModel(val hashMap: HashMap<String, String>) : Parcelable