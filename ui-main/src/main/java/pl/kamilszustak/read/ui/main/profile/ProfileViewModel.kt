package pl.kamilszustak.read.ui.main.profile

import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : BaseViewModel<ProfileEvent, ProfileState>() {
    override fun handleEvent(event: ProfileEvent) {
    }
}