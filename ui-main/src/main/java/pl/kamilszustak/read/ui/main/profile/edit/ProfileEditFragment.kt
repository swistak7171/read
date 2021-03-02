package pl.kamilszustak.read.ui.main.profile.edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.errorToast
import pl.kamilszustak.read.ui.base.util.navigateUp
import pl.kamilszustak.read.ui.base.util.successToast
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentProfileEditBinding
import javax.inject.Inject

class ProfileEditFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentProfileEditBinding, ProfileEditViewModel>(R.layout.fragment_profile_edit) {

    override val viewModel: ProfileEditViewModel by viewModels(viewModelFactory)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile_edit_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.saveItem -> {
                viewModel.dispatch(ProfileEditEvent.OnSaveButtonClicked)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                ProfileEditAction.ProfileEdited -> successToast(R.string.profil_edited_message)
                ProfileEditAction.NavigateUp -> navigateUp()
                is ProfileEditAction.Error -> errorToast(action.messageResourceId)
            }
        }
    }
}