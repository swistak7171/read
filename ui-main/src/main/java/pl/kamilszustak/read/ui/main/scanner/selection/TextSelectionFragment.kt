package pl.kamilszustak.read.ui.main.scanner.selection

import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentTextSelectionBinding
import javax.inject.Inject

class TextSelectionFragment @Inject constructor(

): BaseFragment<FragmentTextSelectionBinding, TextSelectionViewModel>(R.layout.fragment_text_selection) {
    override val viewModel: TextSelectionViewModel
}