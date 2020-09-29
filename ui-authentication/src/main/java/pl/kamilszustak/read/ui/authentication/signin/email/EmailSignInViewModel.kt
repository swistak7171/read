package pl.kamilszustak.read.ui.authentication.signin.email

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.form.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class EmailSignInViewModel @Inject constructor(
    private val formValidator: FormValidator,
    private val firebaseAuth: FirebaseAuth
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
        val email = userEmailAddress.value
        val password = userPassword.value
        val confirmedPassword = confirmedUserPassword.value

        if (email == null || !formValidator.validateEmailAddress(email)) {
            _state.value = EmailSignInState.Error(R.string.invalid_email_address)
            return
        }

        if (password.isNullOrBlank()) {
            _state.value = EmailSignInState.Error(R.string.invalid_password)
            return
        }

        if (confirmedPassword != password) {
            _state.value = EmailSignInState.Error(R.string.not_equal_passwords)
            return
        }

        viewModelScope.launch {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Timber.i(result.user.toString())
        }
    }
}