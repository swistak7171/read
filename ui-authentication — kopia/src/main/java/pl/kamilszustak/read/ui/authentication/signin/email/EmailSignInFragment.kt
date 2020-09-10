package pl.kamilszustak.read.ui.authentication.signin.email

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.authentication.AuthenticationDataBindingFragment
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentEmailSignInBinding
import pl.kamilszustak.read.ui.util.viewModels
import javax.inject.Inject

class EmailSignInFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : AuthenticationDataBindingFragment<FragmentEmailSignInBinding, EmailSignInViewModel>(R.layout.fragment_email_sign_in) {

    override val viewModel: EmailSignInViewModel by viewModels(viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}