package pl.kamilszustak.read.ui.authentication.mainmenu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.support.v4.startActivity
import pl.kamilszustak.read.common.util.asWeakReference
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentMainMenuBinding
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigateTo
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.MainActivity
import javax.inject.Inject

class MainMenuFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
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
            val reference = requireActivity().asWeakReference()
            val event = MainMenuEvent.OnGoogleSignInButtonClicked(reference, webClientId)
            viewModel.dispatchEvent(event)
        }

        binding.facebookSignInButton.setOnClickListener {
            val event = MainMenuEvent.OnFacebookSignInButtonClicked(this.asWeakReference())
            viewModel.dispatchEvent(event)
        }

        binding.twitterSignInButton.setOnClickListener {
            val reference = requireActivity().asWeakReference()
            val event = MainMenuEvent.OnTwitterSignInButtonClicked(reference)
            viewModel.dispatchEvent(event)
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

                is MainMenuState.Error -> {
                    errorToast(state.messageResourceId)
                }

                MainMenuState.Authenticated -> {
                    startActivity<MainActivity>()
                    requireActivity().finish()
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
        googleActivityLauncher?.launch(state.intent)
    }
}