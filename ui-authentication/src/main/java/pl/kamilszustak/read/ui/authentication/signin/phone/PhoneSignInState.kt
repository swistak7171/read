package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.annotation.StringRes
import pl.kamilszustak.read.model.domain.Country
import pl.kamilszustak.read.ui.base.view.ViewState

sealed class PhoneSignInState : ViewState {
    data class CountryPickerOpened(
        val countries: List<Country>,
    ) : PhoneSignInState()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : PhoneSignInState()

    object Authenticated : PhoneSignInState()
}