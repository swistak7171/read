package pl.kamilszustak.read.ui.main.search

import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : BaseViewModel<SearchEvent, SearchAction>() {

    val searchText: UniqueLiveData<String> = UniqueLiveData()

    override fun handleEvent(event: SearchEvent) {
    }
}