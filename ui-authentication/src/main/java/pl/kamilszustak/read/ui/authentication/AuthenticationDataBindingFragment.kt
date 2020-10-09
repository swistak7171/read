package pl.kamilszustak.read.ui.authentication

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.ui.base.view.fragment.DataBindingFragment

abstract class AuthenticationDataBindingFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResourceId: Int,
) : DataBindingFragment<DB, VM>(layoutResourceId) {

    override val viewModelId: Int
        get() = BR.viewModel
}