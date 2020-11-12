package pl.kamilszustak.read.ui.main.search

import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.view.ViewEvent

internal sealed class SearchEvent : ViewEvent {
    object OnResumed : SearchEvent()
    object OnPaused : SearchEvent()
    object OnSearchButtonClicked : SearchEvent()
    object OnScanButtonClicked : SearchEvent()

    data class OnAddToCollectionButtonClicked(
        val volume: Volume,
    ) : SearchEvent()
}