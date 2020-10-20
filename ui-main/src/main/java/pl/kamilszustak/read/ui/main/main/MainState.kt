package pl.kamilszustak.read.ui.main.main

import androidx.annotation.IdRes
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class MainState : ViewState {
    class ChangeFragmentSelection(
        @IdRes val idResource: Int,
    ) : MainState()
}
