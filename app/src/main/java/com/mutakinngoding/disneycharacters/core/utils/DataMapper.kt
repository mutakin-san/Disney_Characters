package com.mutakinngoding.disneycharacters.core.utils

import com.mutakinngoding.disneycharacters.core.data.source.local.model.CharacterModel
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.CharacterDTO
import com.mutakinngoding.disneycharacters.core.domain.entity.Character

object DataMapper {
    fun mapModelToEntity(data: List<CharacterModel>): List<Character> {
        return data.map {
            Character(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                tvShows = it.tvShows,
                shortFilms = it.shortFilms,
                films = it.films,
                sourceUrl = it.sourceUrl,
                parkAttractions = it.parkAttractions,
                videoGames = it.videoGames,
                isFavorite = it.isFavorite
            )
        }
    }

    fun mapDTOtoModels(data: List<CharacterDTO>): List<CharacterModel> {
        return data.map {
            CharacterModel(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                tvShows = it.tvShows,
                shortFilms = it.shortFilms,
                films = it.films,
                sourceUrl = it.sourceUrl ?: "",
                parkAttractions = it.parkAttractions,
                videoGames = it.videoGames
            )
        }
    }

    fun mapEntityToModel(character: Character): CharacterModel {
        return CharacterModel(
            id = character.id,
            name = character.name,
            imageUrl = character.imageUrl,
            tvShows = character.tvShows,
            shortFilms = character.shortFilms,
            films = character.films,
            sourceUrl = character.sourceUrl,
            parkAttractions = character.parkAttractions,
            videoGames = character.videoGames
        )
    }

}
