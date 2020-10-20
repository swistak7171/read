package pl.kamilszustak.read.ui.main.activity

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.ui.base.view.dialog.DataBindingDialogFragment
import pl.kamilszustak.read.ui.main.BR

abstract class MainDataBindingDialogFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResourceId: Int,
) : DataBindingDialogFragment<DB, VM>(layoutResourceId) {

    override val viewModelId: Int
        get() = BR.viewModel
}