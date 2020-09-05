package pl.kamilszustak.read.ui.mainmenu

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.R
import pl.kamilszustak.ui.BaseFragment
import pl.kamilszustak.read.ui.databinding.FragmentMainMenuBinding
import javax.inject.Inject

class MainMenuFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : pl.kamilszustak.ui.BaseFragment<FragmentMainMenuBinding, MainMenuViewModel>(R.layout.fragment_main_menu) {

    override val binding: FragmentMainMenuBinding by viewBinding(FragmentMainMenuBinding::bind)
    override val viewModel: MainMenuViewModel by viewModels(viewModelFactory)
}