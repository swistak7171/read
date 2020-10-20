package pl.kamilszustak.read.ui.authentication.signin.email

import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.support.v4.startActivity
import pl.kamilszustak.read.ui.authentication.AuthenticationDataBindingFragment
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentEmailSignInBinding
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.activity.MainActivity
import javax.inject.Inject

class EmailSignInFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : AuthenticationDataBindingFragment<FragmentEmailSignInBinding, EmailSignInViewModel>(R.layout.fragment_email_sign_in) {

    override val viewModel: EmailSignInViewModel by viewModels(viewModelFactory)

    override fun setListeners() {
        binding.signInButton.setOnClickListener {
            viewModel.dispatchEvent(EmailSignInEvent.OnSignInButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is EmailSignInAction.Error -> {
                    errorToast(action.messageResourceId)
                }

                EmailSignInAction.Authenticated -> {
                    startActivity<MainActivity>()
                    requireActivity().finish()
                }
            }
        }
    }
}