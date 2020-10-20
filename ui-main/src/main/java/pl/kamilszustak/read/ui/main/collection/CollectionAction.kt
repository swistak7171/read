package pl.kamilszustak.read.ui.main.collection

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import pl.kamilszustak.model.common.id.CollectionBookId
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class CollectionAction : ViewAction {
    data class ShowAddBookDialog(
        @ArrayRes val itemsResourceId: Int,
    ) : CollectionAction()

    data class NavigateToBookEditFragment(
        val collectionBookId: CollectionBookId? = null,
    ) : CollectionAction()

    data class NavigateToReadingProgressDialogFragment(
        val collectionBookId: CollectionBookId,
    ) : CollectionAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : CollectionAction()

    object BookDeleted : CollectionAction()
    object NavigateToSearchFragment : CollectionAction()
}