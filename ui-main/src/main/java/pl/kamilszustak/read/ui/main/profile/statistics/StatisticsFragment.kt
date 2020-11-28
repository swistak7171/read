package pl.kamilszustak.read.ui.main.profile.statistics

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.chart.ChartFactory
import pl.kamilszustak.read.ui.main.databinding.FragmentStatisticsBinding
import pl.kamilszustak.read.ui.main.util.show
import javax.inject.Inject

class StatisticsFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentStatisticsBinding, StatisticsViewModel>(R.layout.fragment_statistics) {

    override val viewModel: StatisticsViewModel by viewModels(viewModelFactory)
    private val chartFactory: ChartFactory = ChartFactory()

    override fun observeViewModel() {
        viewModel.weeklyStatistics.observe(viewLifecycleOwner) { statistics ->
            if (statistics == null) return@observe

            statistics.forEach { data ->
                val chart = chartFactory.createBarChart(requireContext()).apply {
                    show(data)
                }

                binding.weekChartsViewFlipper.addView(chart)
            }
        }

        viewModel.monthlyStatistics.observe(viewLifecycleOwner) { statistics ->
            if (statistics == null) return@observe

            statistics.forEach { data ->
                val chart = chartFactory.createBarChart(requireContext()).apply {
                    show(data)
                }

                binding.monthChartsViewFlipper.addView(chart)
            }
        }

        viewModel.currentWeekChart.observe(viewLifecycleOwner) { index ->
            val count = binding.weekChartsViewFlipper.childCount
            if (index == null || index > count - 1) return@observe

            binding.weekChartsViewFlipper.displayedChild = index
        }

        viewModel.currentMonthChart.observe(viewLifecycleOwner) { index ->
            val count = binding.monthChartsViewFlipper.childCount
            if (index == null || index > count - 1) return@observe

            binding.monthChartsViewFlipper.displayedChild = index
        }
    }
}