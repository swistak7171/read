package pl.kamilszustak.read.ui.main.collection

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.list.listItems
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import pl.kamilszustak.model.common.id.BookId
import pl.kamilszustak.read.model.domain.Book
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.*
import pl.kamilszustak.read.ui.base.view.fragment.BaseFragment
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.activity.MainEvent
import pl.kamilszustak.read.ui.main.activity.MainFragmentType
import pl.kamilszustak.read.ui.main.activity.MainViewModel
import pl.kamilszustak.read.ui.main.databinding.FragmentCollectionBinding
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

    private val ITEM_MAX_SCALE: Float = 1.2F
    private val ITEM_MIN_SCALE: Float = 0.8F
    private val ITEM_TRANSITION_TIME_MILLIS: Int = 150

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_collection_fragment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.addBookItem -> {
                viewModel.dispatchEvent(CollectionEvent.OnAddBookButtonClicked)
                true
            }

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
                val currentPosition = binding.booksRecyclerView.currentItem
                if (position != currentPosition) {
                    binding.booksRecyclerView.smoothScrollToPosition(position)
                } else {
                    val event = CollectionEvent.OnBookClicked(item.model.id)
                    viewModel.dispatchEvent(event)
                }

                true
            }

            onLongClickListener = { view, adapter, item, position ->
                val currentPosition = binding.booksRecyclerView.currentItem
                if (position != currentPosition) {
                    binding.booksRecyclerView.smoothScrollToPosition(position)
                } else {
                    popupMenu(view, R.menu.popup_menu_collection_book_item) {
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

                true
            }
        }

        binding.booksRecyclerView.apply {
            setSlideOnFling(true)
            setItemTransformer(ScaleTransformer.Builder()
                .setMaxScale(ITEM_MAX_SCALE)
                .setMinScale(ITEM_MIN_SCALE)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build()
            )
            setItemTransitionTimeMillis(ITEM_TRANSITION_TIME_MILLIS)
            addScrollListener { scrollPosition, currentPosition, newPosition, currentHolder, newCurrent ->
                val event = CollectionEvent.OnScrolled(newPosition)
                viewModel.dispatchEvent(event)
            }
            adapter = fastAdapter
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

                CollectionAction.NavigateToScannerFragment -> {
                    val event = MainEvent.OnFragmentSelectionChanged(MainFragmentType.SCANNER_FRAGMENT)
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

        viewModel.currentBook.observe(viewLifecycleOwner) { book ->
            updateProgress(book.progressPercentage)
            updateDescription(book.description)
        }
    }

    private fun updateProgress(progress: Int) {
        val lastProgress = binding.progressBar.progress
        binding.progressBar.setProgress(progress, true)

        ValueAnimator.ofInt(lastProgress, progress).apply {
            duration = ITEM_TRANSITION_TIME_MILLIS.toLong() * 2
            addUpdateListener { animator ->
                val value = (animator.animatedValue as? Int) ?: return@addUpdateListener
                binding.progressPercentageTextView.text = getString(R.string.progress_percentage, value)
            }
        }.start()
    }

    private fun updateDescription(description: String?) {
        with(binding.descriptionTextView) {
            animate()
                .alpha(0.0F)
                .setDuration(ITEM_TRANSITION_TIME_MILLIS.toLong())
                .withEndAction {
                    text = description
                    animate()
                        .alpha(1.0F)
                        .setDuration(ITEM_TRANSITION_TIME_MILLIS.toLong())
                        .start()
                }
                .start()
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
            val direction = CollectionFragmentDirections.actionCollectionFragmentToBookDetailsFragment(
                bookId.value
            )
            navigate(direction)
        }

        fun navigateToReadingLogFragment() {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToReadingLogFragment()
            navigate(direction)
        }

        fun navigateToReadingProgressDialogFragment(bookId: BookId) {
            val direction = CollectionFragmentDirections.actionCollectionFragmentToReadingProgressDialogFragment(
                bookId.value
            )
            navigate(direction)
        }
    }
}