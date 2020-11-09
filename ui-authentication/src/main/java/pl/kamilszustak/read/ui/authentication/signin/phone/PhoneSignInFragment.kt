package pl.kamilszustak.read.ui.authentication.signin.phone

import android.text.InputType
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItems
import pl.kamilszustak.read.common.util.asWeakReference
import pl.kamilszustak.read.ui.authentication.AuthenticationDataBindingFragment
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentPhoneSignInBinding
import pl.kamilszustak.read.ui.base.util.dialog
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.normalToast
import pl.kamilszustak.read.ui.base.util.viewModels
import javax.inject.Inject

class PhoneSignInFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : AuthenticationDataBindingFragment<FragmentPhoneSignInBinding, PhoneSignInViewModel>(R.layout.fragment_phone_sign_in) {

    override val viewModel: PhoneSignInViewModel by viewModels(viewModelFactory)

    override fun setListeners() {
        binding.countryCodeEditText.setOnClickListener {
            viewModel.dispatchEvent(PhoneSignInEvent.OnCountryEditTextClicked)
        }

        binding.verificationCodeButton.setOnClickListener {
            viewModel.dispatchEvent(PhoneSignInEvent.OnVerificationCodeButtonClicked)
        }

        binding.signInButton.setOnClickListener {
            val reference = requireActivity().asWeakReference()
            val event = PhoneSignInEvent.OnSignInButtonClicked(reference)
            viewModel.dispatchEvent(event)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is PhoneSignInAction.CountryPickerOpened -> {
                    val namesAndCodes = action.countries.map { country ->
                        "(+${country.extension}) ${country.name}"
                    }

                    dialog {
                        listItems(
                            items = namesAndCodes,
                            waitForPositiveButton = false,
                            selection = { dialog, index, text ->
                                val event = PhoneSignInEvent.OnCountrySelected(index)
                                viewModel.dispatchEvent(event)
                            }
                        )
                    }
                }

                is PhoneSignInAction.Error -> {
                    errorToast(action.messageResourceId)
                }

                PhoneSignInAction.OnVerificationCodeSent -> {
                    normalToast(R.string.verification_code_sent)
                }

                PhoneSignInAction.ShowVerificationCodeDialog -> {
                    dialog {
                        title(R.string.enter_verification_code)
                        input(
                            hintRes = R.string.verification_code,
                            inputType = InputType.TYPE_NUMBER_FLAG_SIGNED,
                            maxLength = 6
                        ) { dialog, text ->
                            val event = PhoneSignInEvent.OnVerificationCodeEntered(text.toString())
                            viewModel.dispatchEvent(event)
                        }
                    }
                }
            }
        }
    }
}