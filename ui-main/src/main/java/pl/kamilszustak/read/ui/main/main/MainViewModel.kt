package pl.kamilszustak.read.ui.main.main

import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel<MainEvent, MainState>() {
    init {
        _state.value = MainState.ChangeFragmentSelection(MainFragmentType.COLLECTION_FRAGMENT.itemId)
    }

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnFragmentSelectionChanged -> {
                _state.value = MainState.ChangeFragmentSelection(event.type.itemId)
            }
        }
    }
}