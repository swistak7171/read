package pl.kamilszustak.read.ui.authentication.signin.email

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class EmailSignInViewModel @Inject constructor(
    private val formValidator: FormValidator,
    private val firebaseAuth: FirebaseAuth,
) : BaseViewModel<EmailSignInEvent, EmailSignInAction>() {

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
            _action.value = EmailSignInAction.Error(R.string.invalid_email_address)
            return
        }

        if (password.isNullOrBlank()) {
            _action.value = EmailSignInAction.Error(R.string.invalid_password)
            return
        }

        if (confirmedPassword != password) {
            _action.value = EmailSignInAction.Error(R.string.not_equal_passwords)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            var result = try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            } catch (exception: FirebaseAuthUserCollisionException) {
                val errorAction = EmailSignInAction.Error(R.string.user_with_email_exists)
                _action.postValue(errorAction)
                null
            }

            if (result == null) {
                result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            }

            if (result?.user != null) {
                _action.postValue(EmailSignInAction.Authenticated)
            }
        }
    }
}