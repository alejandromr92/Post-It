package com.example.alejandro.postit.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(var title: String = ""
                , var content: String = ""
 ) : Parcelable