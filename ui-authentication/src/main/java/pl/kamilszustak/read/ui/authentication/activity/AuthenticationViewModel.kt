package pl.kamilszustak.read.ui.authentication.activity

import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : BaseViewModel<AuthenticationEvent, AuthenticationAction>(), FirebaseAuth.AuthStateListener {

    init {
        firebaseAuth.addAuthStateListener(this)
    }

    override fun onCleared() {
        firebaseAuth.removeAuthStateListener(this)
    }

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        if (auth.currentUser != null) {
            _action.value = AuthenticationAction.NavigateToMainActivity
        }
    }
}