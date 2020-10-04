package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentMainMenuBinding
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.navigateTo
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import javax.inject.Inject

class MainMenuFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentMainMenuBinding, MainMenuViewModel>(R.layout.fragment_main_menu) {

    override val binding: FragmentMainMenuBinding by viewBinding(FragmentMainMenuBinding::bind)
    override val viewModel: MainMenuViewModel by viewModels(viewModelFactory)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val event = MainMenuEvent.OnActivityResult(requestCode, resultCode, data)
        viewModel.dispatchEvent(event)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        binding.emailAddressSignInButton.setOnClickListener {
            viewModel.dispatchEvent(MainMenuEvent.OnEmailSignInButtonClicked)
        }

        binding.phoneNumberSignInButton.setOnClickListener {
            viewModel.dispatchEvent(MainMenuEvent.OnPhoneSignInButtonClicked)
        }

        binding.googleSignInButton.setOnClickListener {
            val webClientId = getString(R.string.default_web_client_id)
            val event = MainMenuEvent.OnGoogleSignInButtonClicked(webClientId)
            viewModel.dispatchEvent(event)
        }

        binding.facebookSignInButton.setOnClickListener {
            viewModel.dispatchEvent(MainMenuEvent.OnFacebookSignInButtonClicked)
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                MainMenuState.EmailAuthentication -> {
                    val direction = MainMenuFragmentDirections.actionMainMenuFragmentToEmailSignInFragment()
                    navigateTo(direction)
                }

                MainMenuState.PhoneAuthentication -> {
                    val direction = MainMenuFragmentDirections.actionMainMenuFragmentToPhoneSignInFragment()
                    navigateTo(direction)
                }

                is MainMenuState.FacebookAuthentication -> {
                    handleFacebookAuthentication(state)
                }
            }
        }
    }

    private fun handleFacebookAuthentication(state: MainMenuState.FacebookAuthentication) {
        state.loginManager.logInWithReadPermissions(this, state.permissions)
    }
}