package pl.kamilszustak.read.network

import pl.kamilszustak.read.common.resource.ResourceProvider
import javax.inject.Inject

class GoogleBooksApiHelper @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    val requiredParameters: Map<String, String>
        get() {
            val apiKey = resourceProvider.getString(R.string.google_api_key)

            return mapOf(
                "key" to apiKey,
                "maxResults" to "40",
            )
        }
}