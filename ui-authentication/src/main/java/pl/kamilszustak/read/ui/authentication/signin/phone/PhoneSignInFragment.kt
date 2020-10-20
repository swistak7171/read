package pl.kamilszustak.read.ui.authentication.signin.phone

import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.list.listItems
import org.jetbrains.anko.support.v4.startActivity
import pl.kamilszustak.read.ui.authentication.AuthenticationDataBindingFragment
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentPhoneSignInBinding
import pl.kamilszustak.read.ui.base.util.dialog
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.main.MainActivity
import javax.inject.Inject

class PhoneSignInFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : AuthenticationDataBindingFragment<FragmentPhoneSignInBinding, PhoneSignInViewModel>(R.layout.fragment_phone_sign_in) {

    override val viewModel: PhoneSignInViewModel by viewModels(viewModelFactory)

    override fun setListeners() {
        binding.countryCodeEditText.setOnClickListener {
            viewModel.dispatchEvent(PhoneSignInEvent.OnCountryEditTextClicked)
        }

        binding.signInButton.setOnClickListener {
            viewModel.dispatchEvent(PhoneSignInEvent.OnSignInButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PhoneSignInState.CountryPickerOpened -> {
                    val namesAndCodes = state.countries.map { country ->
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

                is PhoneSignInState.Error -> {
                    errorToast(state.messageResourceId)
                }

                PhoneSignInState.Authenticated -> {
                    startActivity<MainActivity>()
                    requireActivity().finish()
                }
            }
        }
    }
}