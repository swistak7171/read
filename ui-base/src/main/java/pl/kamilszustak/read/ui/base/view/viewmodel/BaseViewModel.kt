package pl.kamilszustak.read.ui.base.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.common.lifecycle.SingleLiveData
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.base.view.ViewAction
import pl.kamilszustak.read.ui.base.view.ViewEvent

abstract class BaseViewModel<E : ViewEvent, A : ViewAction> : ViewModel() {
    protected val _action: SingleLiveData<A> = SingleLiveData()
    val action: LiveData<A>
        get() = _action

    protected val _isLoading: MutableLiveData<Boolean> = UniqueLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    protected open fun handleEvent(event: E) {
    }

    fun dispatch(event: E) {
        handleEvent(event)
    }
}