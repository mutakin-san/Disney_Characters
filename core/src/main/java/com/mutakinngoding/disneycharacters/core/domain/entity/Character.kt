package com.mutakinngoding.disneycharacters.core.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Character (
    val id: Int,
    val name: String,
    val imageUrl: String,
    val tvShows: List<String>,
    val shortFilms: List<String>,
    val films: List<String>,
    val sourceUrl: String,
    val parkAttractions: List<String>,
    val videoGames: List<String>,
    var isFavorite : Boolean = false
): Parcelable