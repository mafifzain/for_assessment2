package org.d3if0060.assessment2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0060.assessment2.data.TokoImage
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "mafifzain/for_assessment2/static-api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TokoApiService {
    @GET("static-api.json")
    suspend fun getImage(): List<TokoImage>
}

object TokoApi {
    val service: TokoApiService by lazy {
        retrofit.create(TokoApiService::class.java)
    }

    fun getTokoUrl(nama:String): String{
        return "$BASE_URL$nama.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED }