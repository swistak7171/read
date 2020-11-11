package pl.kamilszustak.read.ui.authentication.signin.phone

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

sealed class PhoneAuthenticationResult {
    data class OnCodeSent(
        val verificationId: String,
        val token: PhoneAuthProvider.ForceResendingToken,
    ) : PhoneAuthenticationResult()

    data class OnVerificationCompleted(
        val credential: PhoneAuthCredential,
    ) : PhoneAuthenticationResult()

    data class OnVerificationFailed(
        val exception: FirebaseException,
    ) : PhoneAuthenticationResult()
}