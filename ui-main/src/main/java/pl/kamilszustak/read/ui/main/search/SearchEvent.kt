package pl.kamilszustak.read.ui.main.search

import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.view.ViewEvent

sealed class SearchEvent : ViewEvent {
    object OnResumed : SearchEvent()
    object OnPaused : SearchEvent()
    object OnSearchButtonClicked : SearchEvent()

    data class OnAddToCollectionButtonClicked(
        val volume: Volume,
    ) : SearchEvent()
}