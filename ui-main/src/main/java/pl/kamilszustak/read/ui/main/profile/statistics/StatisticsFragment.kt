package pl.kamilszustak.read.ui.main.profile.statistics

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.chart.ChartFactory
import pl.kamilszustak.read.ui.main.databinding.FragmentStatisticsBinding
import pl.kamilszustak.read.ui.main.util.animate
import timber.log.Timber
import javax.inject.Inject

class StatisticsFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentStatisticsBinding, StatisticsViewModel>(R.layout.fragment_statistics) {

    override val viewModel: StatisticsViewModel by viewModels(viewModelFactory)
    private val chartFactory: ChartFactory = ChartFactory()

    override fun setListeners() {
        binding.weekSwitcher.previousButton.setOnClickListener {
            viewModel.dispatchEvent(StatisticsEvent.OnPreviousWeekButtonClicked)
        }

        binding.weekSwitcher.nextButton.setOnClickListener {
            viewModel.dispatchEvent(StatisticsEvent.OnNextWeekButtonClicked)
        }

        binding.monthSwitcher.previousButton.setOnClickListener {
            viewModel.dispatchEvent(StatisticsEvent.OnPreviousMonthButtonClicked)
        }

        binding.monthSwitcher.nextButton.setOnClickListener {
            viewModel.dispatchEvent(StatisticsEvent.OnNextMonthButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.weeklyStatistics.observe(viewLifecycleOwner) { statistics ->
            if (statistics == null) return@observe

            binding.weeklyStatisticsChartView.animate(statistics)
        }

        viewModel.monthlyStatistics.observe(viewLifecycleOwner) { statistics ->
            if (statistics == null) return@observe

            binding.monthlyStatisticsChartView.animate(statistics)
        }
    }
}