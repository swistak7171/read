package pl.kamilszustak.read.ui.main.profile.edit

import androidx.annotation.StringRes
import pl.kamilszustak.read.ui.base.view.ViewAction

sealed class ProfileEditAction : ViewAction {
    object ProfileEdited : ProfileEditAction()
    object NavigateUp : ProfileEditAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : ProfileEditAction()
}