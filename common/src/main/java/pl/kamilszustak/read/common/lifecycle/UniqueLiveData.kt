package pl.kamilszustak.read.common.lifecycle

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData

class UniqueLiveData<T> : MutableLiveData<T> {
    constructor() : super()
    constructor(value: T) : super(value)

    @MainThread
    override fun setValue(value: T?) {
        if (this.value != value) {
            super.setValue(value)
        }
    }

    @WorkerThread
    override fun postValue(value: T?) {
        if (this.value != value) {
            super.postValue(value)
        }
    }
}