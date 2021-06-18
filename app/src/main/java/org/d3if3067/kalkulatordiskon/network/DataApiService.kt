package org.d3if3067.kalkulatordiskon.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3067.kalkulatordiskon.data.dataDiri
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://frowziest-perforato.000webhostapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DataApiService {
    @GET("dataApi.json")
    suspend fun getData() : List<dataDiri>
}

object DataApi {
    val service: DataApiService by lazy {
        retrofit.create(DataApiService::class.java);
    }
    fun getImgProfile(nama: String): String {
        return BASE_URL + "img/$nama.jpg"
    }
}
enum class ApiStatus{
    Loading, Succes, Failure
}