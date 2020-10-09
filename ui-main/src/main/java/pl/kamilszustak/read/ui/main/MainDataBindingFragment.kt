package pl.kamilszustak.read.ui.main

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.ui.base.view.fragment.DataBindingFragment

abstract class MainDataBindingFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResourceId: Int,
) : DataBindingFragment<DB, VM>(layoutResourceId) {

    override val viewModelId: Int
        get() = BR.viewModel
}