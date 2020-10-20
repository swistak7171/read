package pl.kamilszustak.read.ui.main.collection

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class CollectionState : ViewState {
    data class ShowAddBookDialog(
        @ArrayRes val itemsResourceId: Int,
    ) : CollectionState()

    data class NavigateToBookEditFragment(
        val collectionBookId: CollectionBookId? = null,
    ) : CollectionState()

    data class NavigateToReadingProgressDialogFragment(
        val collectionBookId: CollectionBookId,
    ) : CollectionState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : CollectionState()

    object BookDeleted : CollectionState()
    object NavigateToSearchFragment : CollectionState()
}