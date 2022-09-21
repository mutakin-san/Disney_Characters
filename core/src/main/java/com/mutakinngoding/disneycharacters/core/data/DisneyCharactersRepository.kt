package com.mutakinngoding.disneycharacters.core.data

import com.mutakinngoding.disneycharacters.core.data.source.local.LocalDataSource
import com.mutakinngoding.disneycharacters.core.data.source.remote.RemoteDataSource
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.ApiResponse
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.CharacterDTO
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import com.mutakinngoding.disneycharacters.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DisneyCharactersRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IDisneyCharactersRepository {

    override fun getAllCharacters(): Flow<Resource<List<Character>>> {
        return object : NetworkBoundResource<List<Character>, List<CharacterDTO>>() {
            override fun loadFromDB(): Flow<List<Character>> {
                return localDataSource.getAllCharacters().map {
                    DataMapper.mapModelToEntity(it)
                }
            }

            override fun shouldFetch(data: List<Character>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<CharacterDTO>>> {
                return remoteDataSource.getAllCharacters()
            }

            override suspend fun saveCallResult(data: List<CharacterDTO>) {
                val characterList = DataMapper.mapDTOtoModels(data)
                localDataSource.insertCharacter(characterList)
            }
        }.asFlow()
    }

    override fun getFavoriteCharacters(): Flow<List<Character>> {
        return localDataSource.getFavoriteCharacters().map {
            DataMapper.mapModelToEntity(it)
        }
    }

    override suspend fun setFavoriteCharacter(character: Character, state: Boolean) {
        val characterModel = DataMapper.mapEntityToModel(character)
        localDataSource.setFavoriteCharacter(characterModel, state)
    }

}