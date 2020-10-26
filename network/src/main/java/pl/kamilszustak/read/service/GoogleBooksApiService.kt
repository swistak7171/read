package pl.kamilszustak.read.service

import pl.kamilszustak.model.network.VolumeDto
import pl.kamilszustak.model.network.VolumeListWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("$BOOKS_V1_PREFIX/volumes")
    suspend fun searchForVolumes(@Query("q") parameters: String): Response<VolumeListWrapper>

    @GET("$BOOKS_V1_PREFIX/volumes/{id}")
    suspend fun getById(@Path("id") id: String): Response<VolumeDto>

    @GET("$BOOKS_V1_PREFIX/volumes?q=isbn:{isbn}")
    suspend fun getByIsbn(@Path("isbn") isbn: String): Response<VolumeDto>

    companion object {
        private const val BOOKS_V1_PREFIX: String = "/books/v1"
        const val BASE_URL: String = "https://www.googleapis.com"
    }
}