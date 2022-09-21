package com.mutakinngoding.disneycharacters.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DisneyCharactersResponse(
    val data: List<CharacterDTO>
)

data class CharacterDTO(
    val videoGames: List<String>,
    val parkAttractions: List<String>,
    val sourceUrl: String?,
    val films: List<String>,
    val shortFilms: List<String>,
    val tvShows: List<String>,
    val imageUrl: String,
    val name: String,
    @field:SerializedName("_id")
    val id: Int
)

