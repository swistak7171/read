package pl.kamilszustak.read.ui.authentication.signin.phone

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.list.listItems
import pl.kamilszustak.read.ui.authentication.AuthenticationDataBindingFragment
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentPhoneSignInBinding
import pl.kamilszustak.read.ui.base.util.dialog
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.viewModels
import java.util.Locale
import javax.inject.Inject

class PhoneSignInFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : AuthenticationDataBindingFragment<FragmentPhoneSignInBinding, PhoneSignInViewModel>(R.layout.fragment_phone_sign_in) {

    override val viewModel: PhoneSignInViewModel by viewModels(viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        requireActivity().mainExecutor
        binding.countryCodeEditText.setOnClickListener {
            viewModel.dispatchEvent(PhoneSignInEvent.OnCountryEditTextClicked)
        }

        binding.signInButton.setOnClickListener {
            viewModel.dispatchEvent(PhoneSignInEvent.OnSignInButtonClicked)
        }
    }

    private fun observeViewModel() {
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
            }
        }
    }
}