package com.mutakinngoding.disneycharacters.core.data.source.remote.network

import com.mutakinngoding.disneycharacters.core.data.source.remote.response.DisneyCharactersResponse
import retrofit2.http.GET

interface DisneyCharactersService {


    @GET("/characters")
    suspend fun getAllCharacters() : DisneyCharactersResponse
}