package pl.kamilszustak.read.service

import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApiService {
    @GET("$BOOKS_V1_PREFIX/volumes")
    suspend fun searchForVolumes(@Query("q") parameters: String)

    companion object {
        private const val BOOKS_V1_PREFIX: String = "/books/v1"
        const val BASE_URL: String = "https://www.googleapis.com"
    }
}