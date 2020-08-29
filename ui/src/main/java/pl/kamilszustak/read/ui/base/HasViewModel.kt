package pl.kamilszustak.read.ui.base

import androidx.lifecycle.ViewModel

interface HasViewModel<T : ViewModel> {
    val viewModel: T
}