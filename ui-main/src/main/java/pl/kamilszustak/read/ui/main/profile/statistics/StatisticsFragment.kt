package pl.kamilszustak.read.ui.main.profile.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.db.williamchart.pointtooltip.PointTooltip
import com.db.williamchart.view.DonutChartView
import pl.kamilszustak.read.ui.base.OnSwipeListener
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentStatisticsBinding
import pl.kamilszustak.read.ui.main.util.animate
import javax.inject.Inject

class StatisticsFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentStatisticsBinding, StatisticsViewModel>(R.layout.fragment_statistics) {

    override val viewModel: StatisticsViewModel by viewModels(viewModelFactory)

    // TODO: Delete after williamchart update
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
            .also { view ->
                view?.findViewById<View>(R.id.readPagesChartLayout)
                    ?.findViewById<DonutChartView>(R.id.chartView)
                    ?.show(listOf())

                view?.findViewById<View>(R.id.readBooksChartLayout)
                    ?.findViewById<DonutChartView>(R.id.chartView)
                    ?.show(listOf())
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeChartViews()
    }

    private fun initializeChartViews() {
        val accentColor = requireContext().getColor(R.color.colorAccent)
        binding.readPagesChartLayout.chartView.donutColors = intArrayOf(accentColor)
        binding.readBooksChartLayout.chartView.donutColors = intArrayOf(accentColor)

        binding.weeklyStatisticsChartView.tooltip = PointTooltip()
        binding.monthlyStatisticsChartView.tooltip = PointTooltip()
    }

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

        binding.weeklyStatisticsChartView.setOnTouchListener(object : OnSwipeListener(context) {
            override fun onSwipeLeft() {
                viewModel.dispatchEvent(StatisticsEvent.OnWeeklyStatisticsChartSwipedLeft)
            }

            override fun onSwipeRight() {
                viewModel.dispatchEvent(StatisticsEvent.OnWeeklyStatisticsChartSwipedRight)
            }
        })

        binding.monthlyStatisticsChartView.setOnTouchListener(object : OnSwipeListener(context) {
            override fun onSwipeLeft() {
                viewModel.dispatchEvent(StatisticsEvent.OnMonthlyStatisticsChartSwipedLeft)
            }

            override fun onSwipeRight() {
                viewModel.dispatchEvent(StatisticsEvent.OnMonthlyStatisticsChartSwipedRight)
            }
        })
    }

    override fun observeViewModel() {
        viewModel.readPagesStatistics.observe(viewLifecycleOwner) { statistics ->
            statistics ?: return@observe

            with(binding.readPagesChartLayout.chartView) {
                donutTotal = statistics.second
                animate(listOf(statistics.first))
            }
        }

        viewModel.weeklyStatistics.observe(viewLifecycleOwner) { statistics ->
            statistics ?: return@observe

            binding.weeklyStatisticsChartView.animate(statistics)
        }

        viewModel.monthlyStatistics.observe(viewLifecycleOwner) { statistics ->
            statistics ?: return@observe

            with(binding.monthlyStatisticsChartView) {
                // TODO: Delete after williamchart update
                barsColorsList = List(statistics.size) { barsColor }.toList()
                animate(statistics)
            }
        }
    }
}