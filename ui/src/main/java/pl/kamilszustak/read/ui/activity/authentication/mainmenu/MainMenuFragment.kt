package pl.kamilszustak.read.ui.activity.authentication.mainmenu

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.R
import pl.kamilszustak.read.ui.base.BaseFragment
import javax.inject.Inject

class MainMenuFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment<MainMenuViewModel>(R.layout.fragment_main_menu) {

    override val viewModel: MainMenuViewModel by viewModels(viewModelFactory)
}