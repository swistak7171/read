package pl.kamilszustak.read.ui.main.profile

import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val signOut: SignOutUseCase,
) : BaseViewModel<ProfileEvent, ProfileAction>() {

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnSignOutButtonClicked -> signOut()
        }
    }
}