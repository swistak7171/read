package pl.kamilszustak.read.ui.main.activity

import androidx.annotation.IdRes
import pl.kamilszustak.read.ui.main.R

enum class MainFragmentType(
    @IdRes val itemId: Int,
) {
    COLLECTION_FRAGMENT(R.id.navigation_collection),
    SEARCH_FRAGMENT(R.id.navigation_search),
    SCANNER_FRAGMENT(R.id.navigation_scanner),
    QUOTES_FRAGMENT(R.id.navigation_quotes),
    PROFILE_FRAGMENT(R.id.navigation_profile)
}