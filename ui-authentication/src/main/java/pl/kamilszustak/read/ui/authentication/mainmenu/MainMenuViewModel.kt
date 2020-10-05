package pl.kamilszustak.read.ui.authentication.mainmenu

import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): BaseViewModel<MainMenuEvent, MainMenuState>() {

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
                val options = handleGoogleAuthentication(event.webClientId)
                MainMenuState.GoogleAuthentication(options)
            }

            is MainMenuEvent.OnActivityGoogleResult -> {
                handleActivityGoogleResult(event)
                null
            }

            MainMenuEvent.OnFacebookSignInButtonClicked -> {
                handleFacebookAuthentication()
                val permissions = listOf("email", "public_profile")
                MainMenuState.FacebookAuthentication(facebookLoginManager, permissions)
            }

            is MainMenuEvent.OnActivityFacebookResult -> {
                facebookCallbackManager.onActivityResult(event.requestCode, event.resultCode, event.data)
                null
            }
        }
    }

    private fun handleGoogleAuthentication(webClientId: String): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
    }

    private fun handleActivityGoogleResult(event: MainMenuEvent.OnActivityGoogleResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(event.intent) ?: return
        val account = task.result ?: return
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        viewModelScope.launch(Dispatchers.IO) {
            val user = firebaseAuth.signInWithCredential(credential).await()
            Timber.i(user.toString())
        }
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