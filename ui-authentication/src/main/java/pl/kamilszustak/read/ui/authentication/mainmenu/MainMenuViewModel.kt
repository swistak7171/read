package pl.kamilszustak.read.ui.authentication.mainmenu

import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(): BaseViewModel<MainMenuEvent, MainMenuState>() {
    private val facebookLoginManager: LoginManager = LoginManager.getInstance()
    private val facebookCallbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun handleEvent(event: MainMenuEvent) {
        _state.value = when (event) {
            MainMenuEvent.OnEmailSignInButtonClicked -> {
                MainMenuState.EmailAuthentication
            }

            MainMenuEvent.OnPhoneSignInButtonClicked -> {
                MainMenuState.PhoneAuthentication
            }

            is MainMenuEvent.OnGoogleSignInButtonClicked -> {
                handleGoogleAuthentication(event.webClientId)
                MainMenuState.GoogleAuthentication
            }

            MainMenuEvent.OnFacebookSignInButtonClicked -> {
                handleFacebookAuthentication()
                val permissions = listOf("email", "public_profile")
                MainMenuState.FacebookAuthentication(facebookLoginManager, permissions)
            }

            is MainMenuEvent.OnActivityResult -> {
                facebookCallbackManager.onActivityResult(event.requestCode, event.resultCode, event.data)
                null
            }
        }
    }

    private fun handleGoogleAuthentication(webClientId: String) {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
    }

    private fun handleFacebookAuthentication() {
        val facebookCallback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Timber.i("onSuccess")
            }

            override fun onError(error: FacebookException?) {
                Timber.i("onError: ${error?.message}")
            }

            override fun onCancel() {
                Timber.i("onCancel")
            }
        }

        facebookLoginManager.registerCallback(facebookCallbackManager, facebookCallback)
    }
}