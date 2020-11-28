package pl.kamilszustak.read.ui.main.profile.statistics

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentStatisticsBinding
import javax.inject.Inject

class StatisticsFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentStatisticsBinding, StatisticsViewModel>(R.layout.fragment_statistics) {

    override val viewModel: StatisticsViewModel by viewModels(viewModelFactory)
}