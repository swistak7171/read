package pl.kamilszustak.read.ui.main.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.util.hideKeyboard
import pl.kamilszustak.read.ui.base.util.showKeyboard
import pl.kamilszustak.read.ui.base.util.updateModels
import pl.kamilszustak.read.ui.base.util.viewModels
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.activity.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val viewModel: SearchViewModel by viewModels(viewModelFactory)
    private val modelAdapter: ModelAdapter<Volume, VolumeItem> by lazy {
        ModelAdapter { VolumeItem(it) }
    }

    override fun onResume() {
        super.onResume()
        binding.searchEditText.showKeyboard()
    }

    override fun onPause() {
        binding.searchEditText.hideKeyboard()
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeEditText()
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter)
        binding.volumesRecyclerView.apply {
            adapter = fastAdapter
        }
    }

    override fun setListeners() {
        binding.searchButton.setOnClickListener {
            viewModel.dispatchEvent(SearchEvent.OnSearchButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.volumes.observe(viewLifecycleOwner) { volumes ->
            modelAdapter.updateModels(volumes)
        }
    }

    private fun initializeEditText() {
        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.dispatchEvent(SearchEvent.OnSearchButtonClicked)
                true
            } else {
                false
            }
        }
    }
}