package pl.kamilszustak.read.ui.main.search

import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel<SearchEvent, SearchState>() {
    override fun handleEvent(event: SearchEvent) {
    }
}