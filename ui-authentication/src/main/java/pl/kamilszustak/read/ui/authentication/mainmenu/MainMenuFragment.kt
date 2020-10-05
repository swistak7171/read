package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
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
    private var googleActivityLauncher: ActivityResultLauncher<Intent>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val event = MainMenuEvent.OnActivityFacebookResult(requestCode, resultCode, data)
        viewModel.dispatchEvent(event)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
        registerForActivityResults()
    }

    private fun setListeners() {
        binding.emailAddressSignInButton.setOnClickListener {
            viewModel.dispatchEvent(MainMenuEvent.OnEmailSignInButtonClicked)
        }

        binding.phoneNumberSignInButton.setOnClickListener {
            viewModel.dispatchEvent(MainMenuEvent.OnPhoneSignInButtonClicked)
        }

        binding.googleSignInButton.setOnClickListener {
            val webClientId = getString(R.string.google_web_client_id)
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

                is MainMenuState.GoogleAuthentication -> {
                    handleGoogleAuthentication(state)
                }

                is MainMenuState.FacebookAuthentication -> {
                    handleFacebookAuthentication(state)
                }
            }
        }
    }

    private fun registerForActivityResults() {
        val contract = ActivityResultContracts.StartActivityForResult()
        googleActivityLauncher = registerForActivityResult(contract) { result ->
            val intent = result.data ?: return@registerForActivityResult
            val event = MainMenuEvent.OnActivityGoogleResult(intent)
            viewModel.dispatchEvent(event)
        }
    }

    private fun handleGoogleAuthentication(state: MainMenuState.GoogleAuthentication) {
        val client = GoogleSignIn.getClient(requireContext(), state.options)
        googleActivityLauncher?.launch(client.signInIntent)
    }

    private fun handleFacebookAuthentication(state: MainMenuState.FacebookAuthentication) {
        state.loginManager.logInWithReadPermissions(this, state.permissions)
    }
}