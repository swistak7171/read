package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWebException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.OAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): BaseViewModel<MainMenuEvent, MainMenuState>() {

    private val facebookLoginManager: LoginManager = LoginManager.getInstance()
    private val facebookCallbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun handleEvent(event: MainMenuEvent) {
        when (event) {
            MainMenuEvent.OnEmailSignInButtonClicked -> {
                _state.value = MainMenuState.EmailAuthentication
            }

            MainMenuEvent.OnPhoneSignInButtonClicked -> {
                _state.value = MainMenuState.PhoneAuthentication
            }

            is MainMenuEvent.OnGoogleSignInButtonClicked -> {
                val intent = handleGoogleAuthentication(event)
                intent.useOrNull {
                    _state.value = MainMenuState.GoogleAuthentication(it)
                }
            }

            is MainMenuEvent.OnActivityGoogleResult -> {
                handleActivityGoogleResult(event)
            }

            is MainMenuEvent.OnFacebookSignInButtonClicked -> {
                handleFacebookAuthentication(event)
            }

            is MainMenuEvent.OnActivityFacebookResult -> {
                facebookCallbackManager.onActivityResult(event.requestCode, event.resultCode, event.data)
            }

            is MainMenuEvent.OnTwitterSignInButtonClicked -> {
                handleTwitterAuthentication(event)
            }
        }
    }

    private fun handleGoogleAuthentication(event: MainMenuEvent.OnGoogleSignInButtonClicked): Intent? {
        val activity = event.activityReference.getOnce() ?: return null
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(event.webClientId)
            .requestEmail()
            .build()

        val client = GoogleSignIn.getClient(activity, options)

        return client.signInIntent
    }

    private fun handleActivityGoogleResult(event: MainMenuEvent.OnActivityGoogleResult) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(event.intent) ?: return
        val account = task.result ?: return
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

        viewModelScope.launch(Dispatchers.IO) {
            val result = firebaseAuth.signInWithCredential(credential).await()
            if (result?.user != null) {
                _state.postValue(MainMenuState.Authenticated)
            }
        }
    }

    private fun handleFacebookAuthentication(event: MainMenuEvent.OnFacebookSignInButtonClicked) {
        val fragment = event.fragmentReference.getOnce() ?: return
        val permissions = listOf("email", "public_profile")
        val facebookCallback = object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Timber.i("onSuccess")
                _state.value = MainMenuState.Authenticated
            }

            override fun onError(error: FacebookException?) {
                Timber.i("onError: ${error?.message}")
            }

            override fun onCancel() {
                Timber.i("onCancel")
            }
        }

        with(facebookLoginManager) {
            registerCallback(facebookCallbackManager, facebookCallback)
            logInWithReadPermissions(fragment, permissions)
        }
    }

    private fun handleTwitterAuthentication(event: MainMenuEvent.OnTwitterSignInButtonClicked) {
        val provider = OAuthProvider.newBuilder("twitter.com")
            .build()

        val activity = event.activityReference.getOnce() ?: return
        val task = firebaseAuth.startActivityForSignInWithProvider(activity, provider)

        viewModelScope.launch {
            try {
                val result = task.await()
                if (result?.user != null) {
                    _state.postValue(MainMenuState.Authenticated)
                }
            } catch (exception: FirebaseAuthWebException) {
                _state.value = MainMenuState.Error(R.string.twitter_authentication_cancelled_by_user)
            }
        }
    }
}