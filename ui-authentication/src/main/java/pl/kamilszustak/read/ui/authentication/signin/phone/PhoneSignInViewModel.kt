package pl.kamilszustak.read.ui.authentication.signin.phone

import pl.kamilszustak.read.common.form.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class PhoneSignInViewModel @Inject constructor(
    private val formValidator: FormValidator
) : BaseViewModel<PhoneSignInEvent, PhoneSignInState>() {

    val phoneNumber: UniqueLiveData<String> = UniqueLiveData()

    override fun handleEvent(event: PhoneSignInEvent) {
        val number = phoneNumber.value

        if (number.isNullOrBlank() || !formValidator.validatePhoneAddress(number)) {
            _state.value = PhoneSignInState.Error(R.string.invalid_phone_number)
            return
        }
    }
}