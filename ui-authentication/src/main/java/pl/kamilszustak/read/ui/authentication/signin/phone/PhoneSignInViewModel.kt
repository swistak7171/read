package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.common.util.getOnce
import pl.kamilszustak.read.common.util.tryOrNull
import pl.kamilszustak.read.common.util.withIOContext
import pl.kamilszustak.read.common.util.withMainContext
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.model.domain.Country
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PhoneSignInViewModel @Inject constructor(
    private val formValidator: FormValidator,
    private val getAllCountries: GetAllCountriesUseCase,
    private val getDefaultCountry: GetDefaultCountryUseCase,
    private val firebaseAuth: FirebaseAuth,
) : BaseViewModel<PhoneSignInEvent, PhoneSignInAction>() {

    val phoneNumber: UniqueLiveData<String> = UniqueLiveData()
    private val _country: UniqueLiveData<Country> = UniqueLiveData()
    val country: LiveData<Country>
        get() = _country

    private val countries: List<Country> by lazy { getAllCountries() }
    private var verificationId: String? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch { countries }
            launch {
                val defaultCountry = getDefaultCountry()
                _country.postValue(defaultCountry)
            }
        }
    }

    override fun handleEvent(event: PhoneSignInEvent) {
        when (event) {
            PhoneSignInEvent.OnCountryEditTextClicked -> {
                handleCountryChoiceEvent()
            }

            PhoneSignInEvent.OnVerificationCodeButtonClicked -> {
                _action.value = PhoneSignInAction.ShowVerificationCodeDialog
            }

            is PhoneSignInEvent.OnCountrySelected -> {
                handleOnCountrySelectedEvent(event)
            }

            is PhoneSignInEvent.OnSignInButtonClicked -> {
                handleSignInButtonClick(event)
            }

            is PhoneSignInEvent.OnVerificationCodeEntered -> {
                handleVerificationCodeEnter(event)
            }
        }
    }

    private fun handleCountryChoiceEvent() {
        _action.value = PhoneSignInAction.CountryPickerOpened(countries)
    }

    private fun handleOnCountrySelectedEvent(event: PhoneSignInEvent.OnCountrySelected) {
        _country.value = countries.getOrNull(event.index)
    }

    private fun handleSignInButtonClick(event: PhoneSignInEvent.OnSignInButtonClicked) {
        val country = country.value
        val number = phoneNumber.value
        val activity = event.activityReference.getOnce() ?: return

        if (country == null) {
            _action.value = PhoneSignInAction.Error(R.string.not_selected_country_code)
            return
        }

        if (number.isNullOrBlank() || !formValidator.validatePhoneNumber(number)) {
            _action.value = PhoneSignInAction.Error(R.string.invalid_phone_number)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val result = suspendCoroutine<PhoneAuthenticationResult> { continuation ->
                val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                        this@PhoneSignInViewModel.verificationId = verificationId
                        val result = PhoneAuthenticationResult.OnCodeSent(verificationId, token)
                         continuation.resume(result)
                    }

                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        val result = PhoneAuthenticationResult.OnVerificationCompleted(credential)
                        continuation.resume(result)
                    }

                    override fun onVerificationFailed(exception: FirebaseException) {
                        val result = PhoneAuthenticationResult.OnVerificationFailed(exception)
                        continuation.resume(result)
                    }
                }

                val fullNumber = "+${country.extension}$number"
                val options = PhoneAuthOptions.newBuilder()
                    .setPhoneNumber(fullNumber)
                    .setTimeout(60, TimeUnit.SECONDS)
                    .setActivity(activity)
                    .setCallbacks(callbacks)
                    .build()

                PhoneAuthProvider.verifyPhoneNumber(options)
            }

            withMainContext {
                _action.value = when (result) {
                    is PhoneAuthenticationResult.OnCodeSent -> {
                        PhoneSignInAction.OnVerificationCodeSent
                    }

                    is PhoneAuthenticationResult.OnVerificationCompleted -> {
                        PhoneSignInAction.Authenticated
                    }

                    is PhoneAuthenticationResult.OnVerificationFailed -> {
                        PhoneSignInAction.Error(R.string.code_verification_error_message)
                    }
                }
            }
        }
    }

    private fun handleVerificationCodeEnter(event: PhoneSignInEvent.OnVerificationCodeEntered) {
        val id = verificationId
        if (id == null) {
            _action.value = PhoneSignInAction.Error(R.string.verification_code_not_sent)
            return
        }

        val isNumeric = formValidator.validateNumericText(event.code)
        if (!isNumeric) {
            _action.value = PhoneSignInAction.Error(R.string.invalid_verification_code_format)
            return
        }

        val credential = PhoneAuthProvider.getCredential(id, event.code)
        viewModelScope.launch(Dispatchers.Main) {
            val result = withIOContext {
                tryOrNull {
                    firebaseAuth.signInWithCredential(credential)
                        .await()
                }
            }

            if (result?.user == null) {
                _action.value = PhoneSignInAction.Error(R.string.authentication_error_message)
            }
        }
    }
}