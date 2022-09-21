package com.mutakinngoding.disneycharacters.core.data.source.remote

import com.mutakinngoding.disneycharacters.core.data.source.remote.network.DisneyCharactersService
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.ApiResponse
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.CharacterDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val disneyCharactersService: DisneyCharactersService) {
    suspend fun getAllCharacters(): Flow<ApiResponse<List<CharacterDTO>>> {
        return flow {
            try {
                val response = disneyCharactersService.getAllCharacters()
                val dataArray = response.data
                if(dataArray.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(dataArray))
                }
            } catch (e : Exception) {
                emit(ApiResponse.Error(e.localizedMessage ?: e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}