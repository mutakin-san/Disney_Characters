package com.mutakinngoding.disneycharacters.core.data.source.remote

import android.annotation.SuppressLint
import com.mutakinngoding.disneycharacters.core.data.source.remote.network.DisneyCharactersService
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.ApiResponse
import com.mutakinngoding.disneycharacters.core.data.source.remote.response.CharacterDTO
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

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