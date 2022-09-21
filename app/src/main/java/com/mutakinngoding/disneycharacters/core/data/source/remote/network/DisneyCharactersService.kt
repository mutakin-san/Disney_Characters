package com.mutakinngoding.disneycharacters.core.data.source.remote.network

import com.mutakinngoding.disneycharacters.core.data.source.remote.response.DisneyCharactersResponse
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface DisneyCharactersService {


    @GET("/characters")
    suspend fun getAllCharacters() : DisneyCharactersResponse
}