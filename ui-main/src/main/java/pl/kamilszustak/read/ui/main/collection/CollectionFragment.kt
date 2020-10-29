package pl.kamilszustak.read.ui.main.collection

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.list.listItems
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.activity.MainViewModel
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.FragmentCollectionBinding
import pl.kamilszustak.read.ui.main.activity.MainEvent
import pl.kamilszustak.read.ui.main.activity.MainFragmentType
import javax.inject.Inject

class CollectionFragment @Inject constructor(
    viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<FragmentCollectionBinding, CollectionViewModel>(R.layout.fragment_collection) {

    override val viewModel: CollectionViewModel by viewModels(viewModelFactory)
    private val mainViewModel: MainViewModel by activityViewModels(viewModelFactory)
    override val binding: FragmentCollectionBinding by viewBinding(FragmentCollectionBinding::bind)
    private val navigator: Navigator = Navigator()
    private val modelAdapter: ModelAdapter<Book, BookItem> by lazy {
        ModelAdapter { BookItem(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_collection_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.readingLogItem -> {
                viewModel.dispatchEvent(CollectionEvent.OnReadingLogButtonClicked)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun initializeRecyclerView() {
        val fastAdapter = FastAdapter.with(modelAdapter).apply {
            onClickListener = { view, adapter, item, position ->
                val event = CollectionEvent.OnBookClicked(item.model.id)
                viewModel.dispatchEvent(event)
                true
            }

            onLongClickListener = { view, adapter, item, position ->
                val event = CollectionEvent.OnBookLongClicked(item.model.id)
                viewModel.dispatchEvent(event)
                true
            }

            addEventHook(object : ClickEventHook<BookItem>() {
                override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                    return if (viewHolder is BookItem.ViewHolder) {
                        viewHolder.binding.menuButton
                    } else {
                        null
                    }
                }

                override fun onClick(
                    v: View,
                    position: Int,
                    fastAdapter: FastAdapter<BookItem>,
                    item: BookItem
                ) {
                    popupMenu(v, R.menu.popup_menu_collection_book_item) {
                        setForceShowIcon(true)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.updateReadingProgressItem -> {
                                    val event = CollectionEvent.OnUpdateReadingProgressButtonClicked(item.model.id)
                                    viewModel.dispatchEvent(event)
                                    true
                                }

                                R.id.editBookItem -> {
                                    val event = CollectionEvent.OnEditBookButtonClicked(item.model.id)
                                    viewModel.dispatchEvent(event)
                                    true
                                }

                                R.id.deleteBookItem -> {
                                    dialog {
                                        title(R.string.delete_book_dialog_title)
                                        message(R.string.delete_book_dialog_message)
                                        positiveButton(R.string.yes) {
                                            val event = CollectionEvent.OnDeleteBookButtonClicked(item.model.id)
                                            viewModel.dispatchEvent(event)
                                        }
                                        negativeButton(R.string.no) { dialog ->
                                            dialog.dismiss()
                                        }
                                    }
                                    true
                                }

                                else -> {
                                    false
                                }
                            }
                        }
                    }
                }
            })
        }

        binding.booksRecyclerView.apply {
            adapter = fastAdapter
        }
    }

    override fun setListeners() {
        binding.addBookButton.setOnClickListener {
            viewModel.dispatchEvent(CollectionEvent.OnAddBookButtonClicked)
        }
    }

    override fun observeViewModel() {
        viewModel.action.observe(viewLifecycleOwner) { action ->
            when (action) {
                is CollectionAction.ShowAddBookDialog -> {
                    dialog {
                        listItems(
                            res = action.itemsResourceId,
                            waitForPositiveButton = false,
                            selection = { dialog, index, text ->
                                val event = CollectionEvent.OnDialogOptionSelected(index)
                                viewModel.dispatchEvent(event)
                            }
                        )
                    }
                }

                is CollectionAction.NavigateToBookEditFragment -> {
                    navigator.navigateToBookEditFragment(action.bookId)
                }

                is CollectionAction.NavigateToBookDetailsFragment -> {
                    navigator.navigateToBookDetailsFragment(action.bookId)
                }

                CollectionAction.NavigateToReadingLogFragment -> {
                    navigator.navigateToReadingLogFragment()
                }

                is CollectionAction.NavigateToReadingProgressDialogFragment -> {
                    navigator.navigateToReadingProgressDialogFragment(action.bookId)
                }

                CollectionAction.NavigateToSearchFragment -> {
                    val event = MainEvent.OnFragmentSelectionChanged(MainFragmentType.SEARCH_FRAGMENT)
                    mainViewModel.dispatchEvent(event)
                }

                CollectionAction.BookDeleted -> {
                    successToast(R.string.book_deleted)
                }

                is CollectionAction.Error -> {
                    errorToast(action.messageResourceId)
                }
            }
        }

        viewModel.books.observe(viewLifecycleOwner) { books ->
            modelAdapter.updateModels(books)
        }
    }

    private inner class Navigator {
        fun navigateToBookEditFragment(bookId: BookId? = null) {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToNavigationBookEdit(
                bookId = bookId?.value
            )
            navigate(direction)
        }

        fun navigateToBookDetailsFragment(bookId: BookId) {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToBookDetailsFragment(bookId.value)
            navigate(direction)
        }

        fun navigateToReadingLogFragment() {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToReadingLogFragment()
            navigate(direction)
        }

        fun navigateToReadingProgressDialogFragment(bookId: BookId) {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToReadingProgressDialogFragment(bookId.value)
            navigate(direction)
        }
    }
}