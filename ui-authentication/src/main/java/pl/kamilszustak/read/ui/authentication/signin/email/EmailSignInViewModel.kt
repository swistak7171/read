package pl.kamilszustak.read.ui.authentication.signin.email

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.tryOrNull
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class EmailSignInViewModel @Inject constructor(
    private val formValidator: FormValidator,
    private val firebaseAuth: FirebaseAuth,
) : BaseViewModel<EmailSignInEvent, EmailSignInAction>() {

    val userEmailAddress: MutableLiveData<String> = UniqueLiveData()
    val userPassword: MutableLiveData<String> = UniqueLiveData()

    override fun handleEvent(event: EmailSignInEvent) {
        when (event) {
            EmailSignInEvent.OnSignInButtonClicked -> authenticate()
        }
    }

    private fun authenticate() {
        val email = userEmailAddress.value
        val password = userPassword.value

        if (email == null || !formValidator.validateEmailAddress(email)) {
            _action.value = EmailSignInAction.Error(R.string.invalid_email_address)
            return
        }

        if (password.isNullOrBlank()) {
            _action.value = EmailSignInAction.Error(R.string.invalid_password)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            var result = tryOrNull {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            }

            if (result == null) {
                result = tryOrNull {
                    firebaseAuth.signInWithEmailAndPassword(email, password).await()
                }
            }

            if (result?.user != null) {
                _action.postValue(EmailSignInAction.Authenticated)
            } else {
                val action = EmailSignInAction.Error(R.string.invalid_credentials)
                _action.postValue(action)
            }
        }
    }
}