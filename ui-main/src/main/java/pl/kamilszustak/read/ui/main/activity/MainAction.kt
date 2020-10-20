package pl.kamilszustak.read.ui.main.activity

import androidx.annotation.IdRes
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class MainAction : ViewAction {
    class ChangeFragmentSelection(
        @IdRes val idResource: Int,
    ) : MainAction()
}
