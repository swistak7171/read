package pl.kamilszustak.read.ui.splashscreen.activity

import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : BaseViewModel<SplashScreenEvent, SplashScreenAction>(), FirebaseAuth.AuthStateListener {

    init {
        firebaseAuth.addAuthStateListener(this)
    }

    override fun onCleared() {
        firebaseAuth.removeAuthStateListener(this)
    }

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        _action.value = if (auth.currentUser == null) {
            SplashScreenAction.NavigateToAuthenticationActivity
        } else {
            SplashScreenAction.NavigateToMainActivity
        }
    }
}