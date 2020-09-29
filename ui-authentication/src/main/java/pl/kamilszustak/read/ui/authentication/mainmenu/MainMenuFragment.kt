package pl.kamilszustak.read.ui.authentication.mainmenu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.databinding.FragmentMainMenuBinding
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.navigateTo
import pl.kamilszustak.read.ui.base.util.viewModels
import javax.inject.Inject

class MainMenuFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<FragmentMainMenuBinding, MainMenuViewModel>(R.layout.fragment_main_menu) {

    override val binding: FragmentMainMenuBinding by viewBinding(FragmentMainMenuBinding::bind)
    override val viewModel: MainMenuViewModel by viewModels(viewModelFactory)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        observeViewModel()
    }

    private fun setListeners() {
        binding.emailAddressSignInButton.setOnClickListener {
            viewModel.handleEvent(MainMenuEvent.OnEmailSignInButtonClicked)
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                MainMenuState.EmailAuthentication -> {
                    val direction = MainMenuFragmentDirections.actionMainMenuFragmentToEmailSignInFragment()
                    navigateTo(direction)
                }
            }
        }
    }
}