package es.rfvl.dogs

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsService {

    @GET("{breed}/images")
    suspend fun getDogs(@Path("breed") breed: String): Response<APIResponse>

    @GET("{breed}/images")
    suspend fun updateDogs(@Path("breed") breed: String): Response<APIResponse>
}