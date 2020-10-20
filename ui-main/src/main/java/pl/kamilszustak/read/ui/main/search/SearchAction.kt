package pl.kamilszustak.read.ui.main.search

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class SearchAction : ViewAction {
    data class Error(
        @StringRes val messageResourceId: Int,
    ) : SearchAction()
}