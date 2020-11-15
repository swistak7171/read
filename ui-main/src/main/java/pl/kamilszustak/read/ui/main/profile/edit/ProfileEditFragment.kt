package pl.kamilszustak.read.ui.main.profile.edit

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentProfileEditBinding
import javax.inject.Inject

class ProfileEditFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentProfileEditBinding, ProfileEditViewModel>(R.layout.fragment_profile_edit) {

    override val viewModel: ProfileEditViewModel by viewModels(viewModelFactory)
}