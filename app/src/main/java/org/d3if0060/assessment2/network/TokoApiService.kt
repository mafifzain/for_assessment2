package org.d3if0060.assessment2.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "indraazimi/galeri-hewan/static-api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TokoApiService {
    @GET("static-api.json")
    suspend fun getImage(): String
}

object TokoApi {
    val service: TokoApiService by lazy {
        retrofit.create(TokoApiService::class.java)
    }
}