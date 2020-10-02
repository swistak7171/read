package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

        viewModelScope.launch(Dispatchers.IO) {
            val callbacks = suspendCoroutine<PhoneAuthenticationResult> { continuation ->
                val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onCodeSent(p0: String, token: PhoneAuthProvider.ForceResendingToken) {
                        Timber.i("onCodeSent")
                        continuation.resume(PhoneAuthenticationResult.OnCodeSent)
                    }

                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        Timber.i("onVerificationCompleted")
                        continuation.resume(PhoneAuthenticationResult.OnVerificationCompleted)
                    }

                    override fun onVerificationFailed(exception: FirebaseException) {
                        Timber.i("onVerificationFailed")
                        continuation.resumeWithException(exception)
                    }
                }

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    number,
                    60,
                    TimeUnit.SECONDS,
                    Executors.newSingleThreadExecutor(),
                    callbacks
                )
            }
        }
    }
}