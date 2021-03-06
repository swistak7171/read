package pl.kamilszustak.read.ui.main.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.dialog
import pl.kamilszustak.read.ui.base.util.navigate
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentProfileBinding
import pl.kamilszustak.read.ui.main.util.animate
import javax.inject.Inject

class ProfileFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel: ProfileViewModel by viewModels(viewModelFactory)
    private val navigator: Navigator = Navigator()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        android.R.array.emailAddressTypes
        return when (item.itemId) {
            R.id.editProfileItem -> {
                viewModel.dispatch(ProfileEvent.OnEditButtonClicked)
                true
            }

            R.id.signOutItem -> {
                showSignOutDialog()
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

    override fun setListeners() {
        binding.moreStatisticsButton.setOnClickListener {
            viewModel.dispatch(ProfileEvent.OnMoreStatisticsButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                ProfileAction.NavigateToProfileEditFragment -> navigator.navigateToUserEditFragment()
                ProfileAction.NavigateToStatisticsFragment -> navigator.navigateToStatisticsFragment()
            }
        }

        viewModel.monthlyStatistics.observe(viewLifecycleOwner) { statistics ->
            statistics ?: return@observe

            with(binding.statisticsChartView) {
                barsColorsList = List(statistics.size) { barsColor }.toList()
                animate(statistics)
            }
        }
    }

    private fun showSignOutDialog() {
        dialog {
            title(R.string.sign_out_and_exit)
            message(R.string.sign_out_and_exit_dialog_message)
            positiveButton(R.string.yes) { viewModel.dispatch(ProfileEvent.OnSignOutButtonClicked) }
            negativeButton(R.string.no) { it.dismiss() }
        }
    }

    private inner class Navigator {
        fun navigateToUserEditFragment() {
            val direction = ProfileFragmentDirections.actionProfileFragmentToProfileEditFragment()
            navigate(direction)
        }

        fun navigateToStatisticsFragment() {
            val direction = ProfileFragmentDirections.actionProfileFragmentToStatisticsFragment()
            navigate(direction)
        }
    }
}