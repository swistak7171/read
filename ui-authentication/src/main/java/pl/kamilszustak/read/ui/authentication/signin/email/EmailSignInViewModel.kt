package pl.kamilszustak.read.ui.authentication.signin.email

import pl.kamilszustak.read.common.form.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class EmailSignInViewModel @Inject constructor(
    private val formValidator: FormValidator
) : BaseViewModel<EmailSignInEvent, EmailSignInState>() {

    val userEmailAddress: UniqueLiveData<String> = UniqueLiveData()
    val userPassword: UniqueLiveData<String> = UniqueLiveData()
    val confirmedUserPassword: UniqueLiveData<String> = UniqueLiveData()

    override fun handleEvent(event: EmailSignInEvent) {
        when (event) {
            EmailSignInEvent.OnSignInButtonClicked -> authenticate()
        }
    }

    private fun authenticate() {

    }
}