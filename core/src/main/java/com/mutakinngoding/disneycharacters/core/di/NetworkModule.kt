package com.mutakinngoding.disneycharacters.core.di

import com.mutakinngoding.disneycharacters.core.BuildConfig
import com.mutakinngoding.disneycharacters.core.data.source.remote.network.DisneyCharactersService
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val hostname = BASE_URL.removePrefix("https://").removeSuffix("/")
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/r6NKPGIgMidjJuLcWUyWnCPeGEU5feUJmUdxBpDPpVY=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        val loggingInterceptor =
            HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }


    @Provides
    fun provideApiService(client: OkHttpClient): DisneyCharactersService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(DisneyCharactersService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.disneyapi.dev/"
    }
}