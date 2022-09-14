package com.mutakinngoding.disneycharacters.core.domain.entity

data class Character (
    val id: Int,
    val name: String,
    val imageUrl: String,
    val tvShows: List<String>,
    val shortFilms: List<String>,
    val films: List<String>,
    val sourceUrl: String,
    val parkAttractions: List<String>,
    val videoGames: List<String>
)