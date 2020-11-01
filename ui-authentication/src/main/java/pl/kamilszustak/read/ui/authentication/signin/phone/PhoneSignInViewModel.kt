package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.country.GetAllCountriesUseCase
import pl.kamilszustak.read.domain.access.usecase.country.GetDefaultCountryUseCase
import pl.kamilszustak.read.model.domain.Country
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class PhoneSignInViewModel @Inject constructor(
    private val formValidator: FormValidator,
    private val getAllCountries: GetAllCountriesUseCase,
    private val getDefaultCountry: GetDefaultCountryUseCase,
) : BaseViewModel<PhoneSignInEvent, PhoneSignInAction>() {

    val phoneNumber: UniqueLiveData<String> = UniqueLiveData()
    private val _country: UniqueLiveData<Country> = UniqueLiveData()
    val country: LiveData<Country>
        get() = _country

    private val countries: List<Country> by lazy {
        getAllCountries()
    }

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
            PhoneSignInEvent.OnCountryEditTextClicked -> handleCountryChoiceEvent()
            is PhoneSignInEvent.OnCountrySelected -> handleOnCountrySelectedEvent(event)
            PhoneSignInEvent.OnSignInButtonClicked -> handleSignInEvent()
        }
    }

    private fun handleCountryChoiceEvent() {
        _action.value = PhoneSignInAction.CountryPickerOpened(countries)
    }

    private fun handleOnCountrySelectedEvent(event: PhoneSignInEvent.OnCountrySelected) {
        _country.value = countries.getOrNull(event.index)
    }

    private fun handleSignInEvent() {
        val country = country.value
        val number = phoneNumber.value

        if (country == null) {
            _action.value = PhoneSignInAction.Error(R.string.not_selected_country_code)
            return
        }

        if (number.isNullOrBlank() || !formValidator.validatePhoneAddress(number)) {
            _action.value = PhoneSignInAction.Error(R.string.invalid_phone_number)
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            suspendCoroutine<PhoneAuthenticationResult> { continuation ->
                val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onCodeSent(p0: String, token: PhoneAuthProvider.ForceResendingToken) {
                        Timber.i("onCodeSent")
                        // continuation.resume(PhoneAuthenticationResult.OnCodeSent)
                    }

                    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                        Timber.i("onVerificationCompleted")
                        _action.postValue(PhoneSignInAction.Authenticated)
                        // continuation.resume(PhoneAuthenticationResult.OnVerificationCompleted)
                    }

                    override fun onVerificationFailed(exception: FirebaseException) {
                        Timber.i("onVerificationFailed: ${exception.message}")
                        // continuation.resumeWithException(exception)
                    }
                }

                val fullNumber = "+${country.extension}$number"
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                    fullNumber,
//                    60,
//                    TimeUnit.SECONDS,
//                    TaskExecutors.MAIN_THREAD,
//                    callbacks
//                )
            }
        }
    }
}