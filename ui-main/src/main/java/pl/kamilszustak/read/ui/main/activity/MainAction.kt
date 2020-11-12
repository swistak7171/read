package pl.kamilszustak.read.ui.main.activity

import androidx.annotation.IdRes
import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class MainAction : ViewAction {
    object NavigateToAuthenticationActivity : MainAction()

    class ChangeFragmentSelection(
        @IdRes val idResource: Int,
    ) : MainAction()
}
