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
import javax.inject.Inject
import javax.inject.Singleton

class RemoteDataSource @Inject constructor(private val disneyCharactersService: DisneyCharactersService) {
    @SuppressLint("CheckResult")
    fun getAllCharacters(): Flowable<ApiResponse<List<CharacterDTO>>> {
        val resultData = PublishSubject.create<ApiResponse<List<CharacterDTO>>>()

        val client = disneyCharactersService.getAllCharacters()

        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                resultData.onNext(
                    if (response.data.isEmpty())
                        ApiResponse.Empty
                    else ApiResponse.Success(
                        response.data
                    )
                )
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.localizedMessage ?: error.toString()))
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}