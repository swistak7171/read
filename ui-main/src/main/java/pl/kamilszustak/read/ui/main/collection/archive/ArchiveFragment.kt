package pl.kamilszustak.read.ui.main.collection.archive

import android.os.Build
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.LongClickListener
import com.mikepenz.fastadapter.adapters.ModelAdapter
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.main.MainDataBindingFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.collection.BookDialogOwner
import pl.kamilszustak.read.ui.main.collection.BookDialogOwnerImpl
import pl.kamilszustak.read.ui.main.databinding.FragmentArchiveBinding
import javax.inject.Inject

class ArchiveFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory
): MainDataBindingFragment<FragmentArchiveBinding, ArchiveViewModel>(R.layout.fragment_archive),
   BookDialogOwner by BookDialogOwnerImpl() {

    override val viewModel: ArchiveViewModel by viewModels(viewModelFactory)
    private val modelAdapter: ModelAdapter<Book, ArchivedBookItem> by lazy {
        ModelAdapter { ArchivedBookItem(it) }
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            onLongClickListener = object : LongClickListener<ArchivedBookItem> {
                override fun invoke(
                    v: View,
                    adapter: IAdapter<ArchivedBookItem>,
                    item: ArchivedBookItem,
                    position: Int
                ): Boolean {
                    popupMenu(v, R.menu.popup_menu_archived_book_item) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            setForceShowIcon(true)
                        }

                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.unarchiveBookItem -> {
                                    val event = ArchiveEvent.OnUnarchiveBookButtonClicked(item.model.id)
                                    viewModel.dispatch(event)
                                    true
                                }

                                R.id.deleteBookItem -> {
                                    showDeleteBookDialog(
                                        onPositiveButtonClick = {
                                            val event = ArchiveEvent.OnDeleteBookDialogPositiveButtonClick(item.model.id)
                                            viewModel.dispatch(event)
                                        },
                                        onNegativeButtonClick = { it.dismiss() }
                                    )
                                    true
                                }

                                else -> {
                                    false
                                }
                            }
                        }
                    }

                    return true
                }
            }
        }
        binding.booksRecyclerView.adapter = fastAdapter
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is ArchiveAction.ShowSuccessToast -> {
                    successToast(action.messageResourceId)
                }

                is ArchiveAction.ShowErrorToast -> {
                    errorToast(action.messageResourceId)
                }
            }
        }

        viewModel.books.observe(viewLifecycleOwner) { books ->
            binding.emptyArchiveLayout.root.isVisible = books.isEmpty()
            modelAdapter.updateModels(books)
        }
    }
}