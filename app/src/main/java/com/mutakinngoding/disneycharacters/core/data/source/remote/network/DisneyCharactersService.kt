package com.mutakinngoding.disneycharacters.core.data.source.remote.network

import com.mutakinngoding.disneycharacters.core.data.source.remote.response.DisneyCharactersResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface DisneyCharactersService {


    @GET("/characters")
    fun getAllCharacters() : Flowable<DisneyCharactersResponse>
}