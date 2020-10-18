package pl.kamilszustak.read.ui.base.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.common.lifecycle.SingleLiveData
import pl.kamilszustak.read.ui.base.view.ViewEvent
import pl.kamilszustak.read.ui.base.view.ViewState

abstract class BaseViewModel<E : ViewEvent, S : ViewState> : ViewModel() {
    protected val _state: SingleLiveData<S> = SingleLiveData()
    val state: LiveData<S>
        get() = _state

    protected abstract fun handleEvent(event: E)

    fun dispatchEvent(event: E) {
        handleEvent(event)
    }
}