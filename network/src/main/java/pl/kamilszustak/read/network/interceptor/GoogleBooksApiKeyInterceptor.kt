package pl.kamilszustak.read.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import pl.kamilszustak.read.network.GoogleBooksApiHelper
import javax.inject.Inject

class GoogleBooksApiKeyInterceptor @Inject constructor(
    private val apiHelper: GoogleBooksApiHelper,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val urlBuilder = originalRequest.url.newBuilder()
        apiHelper.requiredParameters.forEach { (key, value) ->
            urlBuilder.addQueryParameter(key, value)
        }

        val url = urlBuilder.build()
        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}