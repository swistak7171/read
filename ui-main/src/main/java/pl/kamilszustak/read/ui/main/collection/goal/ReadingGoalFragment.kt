package pl.kamilszustak.read.ui.main.collection.goal

import androidx.lifecycle.ViewModelProvider
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentReadingGoalBinding
import javax.inject.Inject

class ReadingGoalFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentReadingGoalBinding, ReadingGoalViewModel>(R.layout.fragment_reading_goal) {

    override val viewModel: ReadingGoalViewModel by viewModels(viewModelFactory)
}