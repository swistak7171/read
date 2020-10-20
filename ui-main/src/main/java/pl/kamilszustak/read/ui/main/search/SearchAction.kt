package pl.kamilszustak.read.ui.main.search

import androidx.annotation.StringRes
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class SearchAction : ViewAction {
    object ShowKeyboard : SearchAction()
    object HideKeyboard : SearchAction()
    object NavigateToScannerFragment : SearchAction()

    data class NavigateToBookEditFragment(
        val volume: Volume,
    ) : SearchAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : SearchAction()
}