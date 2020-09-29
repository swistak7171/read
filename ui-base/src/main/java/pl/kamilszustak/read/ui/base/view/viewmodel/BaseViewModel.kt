package pl.kamilszustak.read.ui.base.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.common.lifecycle.SingleLiveData
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.Event
import pl.kamilszustak.read.ui.base.view.State

abstract class BaseViewModel<E : Event, S : State> : ViewModel() {
    protected val _state: SingleLiveData<S> = SingleLiveData()
    val state: LiveData<S>
        get() = _state

    abstract fun handleEvent(event: E)
}