package pl.kamilszustak.read.ui.main.activity

import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel<MainEvent, MainAction>() {
    init {
        _action.value = MainAction.ChangeFragmentSelection(MainFragmentType.COLLECTION_FRAGMENT.itemId)
    }

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnFragmentSelectionChanged -> {
                _action.value = MainAction.ChangeFragmentSelection(event.type.itemId)
            }
        }
    }
}