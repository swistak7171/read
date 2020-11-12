package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.annotation.StringRes
import pl.kamilszustak.read.model.domain.Country
import pl.kamilszustak.read.ui.base.view.ViewAction

internal sealed class PhoneSignInAction : ViewAction {
    data class CountryPickerOpened(
        val countries: List<Country>,
    ) : PhoneSignInAction()

    data class Error(
        @StringRes val messageResourceId: Int,
    ) : PhoneSignInAction()

    object ShowVerificationCodeDialog : PhoneSignInAction()
    object OnVerificationCodeSent : PhoneSignInAction()
    object Authenticated : PhoneSignInAction()
}