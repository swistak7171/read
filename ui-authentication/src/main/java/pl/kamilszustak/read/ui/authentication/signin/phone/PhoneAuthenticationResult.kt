package pl.kamilszustak.read.ui.authentication.signin.phone

sealed class PhoneAuthenticationResult {
    object OnCodeSent : PhoneAuthenticationResult()
    object OnVerificationCompleted : PhoneAuthenticationResult()
    object OnVerificationFailed : PhoneAuthenticationResult()
}