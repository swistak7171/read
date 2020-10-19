package pl.kamilszustak.read.interceptor

import android.app.Application
import okhttp3.Interceptor
import okhttp3.Response
import pl.kamilszustak.read.network.R
import javax.inject.Inject

class GoogleBooksApiKeyInterceptor @Inject constructor(
    private val application: Application,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val apiKey = application.getString(R.string.google_api_key)
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}