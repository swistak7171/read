package pl.kamilszustak.read.ui.authentication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import pl.kamilszustak.read.ui.base.DataBindingFragment

abstract class AuthenticationDataBindingFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResourceId: Int
) : DataBindingFragment<DB, VM>(layoutResourceId) {

    override fun createBinding(
        inflater: LayoutInflater,
        layoutId: Int,
        parent: ViewGroup?,
        attachToParent: Boolean
    ): DB {
        return DataBindingUtil.inflate<DB>(
            inflater,
            layoutResourceId,
            parent,
            attachToParent
        ).apply {
            this.setVariable(BR.viewModel, viewModel)
            this.lifecycleOwner = viewLifecycleOwner
        }
    }
}