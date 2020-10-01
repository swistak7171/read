package pl.kamilszustak.read.ui.authentication.signin.phone

import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class PhoneSignInViewModel @Inject constructor() : BaseViewModel<PhoneSignInEvent, PhoneSignInState>() {

    val phoneNumber: UniqueLiveData<String> = UniqueLiveData()

    override fun handleEvent(event: PhoneSignInEvent) {
    }
}