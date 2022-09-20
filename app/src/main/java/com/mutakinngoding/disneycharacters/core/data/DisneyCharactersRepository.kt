package com.mutakinngoding.disneycharacters.core.data

import com.mutakinngoding.disneycharacters.core.data.source.local.LocalDataSource
import com.mutakinngoding.disneycharacters.core.data.source.remote.RemoteDataSource
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.ApiResponse
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.CharacterDTO
import com.mutakinngoding.disneycharacters.core.domain.entity.Character
import com.mutakinngoding.disneycharacters.core.domain.repository.IDisneyCharactersRepository
import com.mutakinngoding.disneycharacters.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DisneyCharactersRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IDisneyCharactersRepository {

    override fun getAllCharacters(): Flowable<Resource<List<Character>>> {
        return object : NetworkBoundResource<List<Character>, List<CharacterDTO>>() {
            override fun loadFromDB(): Flowable<List<Character>> {
                return localDataSource.getAllCharacters().map {
                    DataMapper.mapModelToEntity(it)
                }
            }

            override fun shouldFetch(data: List<Character>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun createCall(): Flowable<ApiResponse<List<CharacterDTO>>> {
                return remoteDataSource.getAllCharacters()
            }

            override fun saveCallResult(data: List<CharacterDTO>) {
                val characterList = DataMapper.mapDTOtoModels(data)
                localDataSource.insertCharacter(characterList)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            }
        }.asFlowable()
    }

    override fun getFavoriteCharacters(): Flowable<List<Character>> {
        return localDataSource.getFavoriteCharacters().map {
            DataMapper.mapModelToEntity(it)
        }
    }

    override fun setFavoriteCharacter(character: Character, state: Boolean) {
        val characterModel = DataMapper.mapEntityToModel(character)
        localDataSource.setFavoriteCharacter(characterModel, state)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}