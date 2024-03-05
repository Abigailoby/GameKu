package com.duktiv.gameku

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val poto: Int,
    val nama: String,
    val desc: String,
    val spek: String
) : Parcelable
