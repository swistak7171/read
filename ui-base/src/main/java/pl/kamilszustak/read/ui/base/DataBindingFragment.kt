package pl.kamilszustak.read.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class DataBindingFragment<DB : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResourceId: Int
) : BaseFragment<DB, VM>() {

    private var _binding: DB? = null
    override val binding: DB
        get() = _binding ?: throw IllegalStateException("DataBinding cannot be accessed after onDestroyView()")

    abstract fun createBinding(inflater: LayoutInflater,
        layoutId: Int, parent: ViewGroup?, attachToParent: Boolean): DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, layoutResourceId, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}