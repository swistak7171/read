package pl.kamilszustak.read.ui.main.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.activity.MainEvent
import pl.kamilszustak.read.ui.main.activity.MainFragmentType
import pl.kamilszustak.read.ui.main.activity.MainViewModel
import pl.kamilszustak.read.ui.main.databinding.FragmentSearchBinding
import javax.inject.Inject

class SearchFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : MainDataBindingFragment<FragmentSearchBinding, SearchViewModel>(R.layout.fragment_search) {

    override val viewModel: SearchViewModel by viewModels(viewModelFactory)
    private val mainViewModel: MainViewModel by activityViewModels(viewModelFactory)
    private val navigator: Navigator = Navigator()
    private val modelAdapter: ModelAdapter<Volume, VolumeItem> by lazy {
        ModelAdapter { VolumeItem(it) }
    }

    override fun onResume() {
        super.onResume()
        viewModel.dispatchEvent(SearchEvent.OnResumed)
    }

    override fun onPause() {
        viewModel.dispatchEvent(SearchEvent.OnPaused)
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeEditText()
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            addEventHook(object : ClickEventHook<VolumeItem>() {
                override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                    return if (viewHolder is VolumeItem.ViewHolder) {
                        viewHolder.binding.addToCollectionButton
                    } else {
                        null
                    }
                }

                override fun onClick(
                    v: View,
                    position: Int,
                    fastAdapter: FastAdapter<VolumeItem>,
                    item: VolumeItem
                ) {
                    val event = SearchEvent.OnAddToCollectionButtonClicked(item.model)
                    viewModel.dispatchEvent(event)
                }
            })
        }

        binding.volumesRecyclerView.apply {
            adapter = fastAdapter
        }
    }

    override fun setListeners() {
        binding.scanButton.setOnClickListener {
            viewModel.dispatchEvent(SearchEvent.OnScanButtonClicked)
        }

        binding.searchButton.setOnClickListener {
            viewModel.dispatchEvent(SearchEvent.OnSearchButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                SearchAction.ShowKeyboard -> {
                    binding.searchEditText.showKeyboard()
                }

                SearchAction.HideKeyboard -> {
                    binding.searchEditText.hideKeyboard()
                }

                SearchAction.NavigateToScannerFragment -> {
                    val event = MainEvent.OnFragmentSelectionChanged(MainFragmentType.SCANNER_FRAGMENT)
                    mainViewModel.dispatchEvent(event)
                }

                is SearchAction.NavigateToBookEditFragment -> {
                    navigator.navigateToBookEditFragment(action.volume)
                }

                is SearchAction.Error -> {
                    errorToast(action.messageResourceId)
                }
            }
        }

        viewModel.volumes.observe(viewLifecycleOwner) { volumes ->
            if (volumes != null) {
                modelAdapter.updateModels(volumes)
            }
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

    private inner class Navigator {
        fun navigateToBookEditFragment(volume: Volume) {
            val direction = SearchFragmentDirections.actionSearchFragmentToNavigationBookEdit(volume = volume)
            navigate(direction)
        }
    }
}