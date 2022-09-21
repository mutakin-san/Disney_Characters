package com.mutakinngoding.disneycharacters.core.data.source.local.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "characters")
data class CharacterModel(
    @PrimaryKey
    @NonNull
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
)


class ListConverter {
    @TypeConverter
    fun fromListToString(list :List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun stringToList(s: String): List<String> {
        return s.split(",")
    }
}
