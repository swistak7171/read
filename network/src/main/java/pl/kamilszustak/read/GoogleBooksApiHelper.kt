package pl.kamilszustak.read

import android.app.Application
import pl.kamilszustak.read.network.R
import javax.inject.Inject

class GoogleBooksApiHelper @Inject constructor(
    private val application: Application,
) {
    val requiredParameters: Map<String, String>
        get() {
            val apiKey = application.getString(R.string.google_api_key)

            return mapOf(
                "key" to apiKey,
                "maxResults" to "40",
            )
        }
}