package pl.kamilszustak.read.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pl.kamilszustak.model.common.VolumeSearchParameterType
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.volume.SearchForVolumesUseCase
import pl.kamilszustak.read.model.domain.Volume
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchForVolumes: SearchForVolumesUseCase,
) : BaseViewModel<SearchEvent, SearchAction>() {

    private var lastSearchQuery: String = ""
    val searchQuery: UniqueLiveData<String> = UniqueLiveData()

    private val _volumes: UniqueLiveData<List<Volume>> = UniqueLiveData()
    val volumes: LiveData<List<Volume>>
        get() = _volumes

    override fun handleEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.OnResumed -> {
                _action.value = SearchAction.ShowKeyboard
            }

            SearchEvent.OnPaused -> {
                _action.value = SearchAction.HideKeyboard
            }

            SearchEvent.OnSearchButtonClicked -> {
                handleSearchButtonClick()
            }

            is SearchEvent.OnAddToCollectionButtonClicked -> {
                _action.value = SearchAction.NavigateToBookEditFragment(event.volume)
            }
        }
    }

    private fun handleSearchButtonClick() {
        val query = searchQuery.value
        if (query.isNullOrBlank()) {
            _action.value = SearchAction.Error(R.string.blank_search_query_error_message)
            return
        }

        if (query == lastSearchQuery) {
            return
        } else {
            lastSearchQuery = query
        }

        viewModelScope.launch(Dispatchers.Main) {
            val parameters = mapOf(VolumeSearchParameterType.GENERAL to query)
            searchForVolumes(parameters)
                .collect { _volumes.value = it }
        }
    }
}