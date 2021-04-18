package pl.kamilszustak.read.ui.main.collection

import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import pl.kamilszustak.read.ui.base.util.dialog
import pl.kamilszustak.read.ui.main.R

class BookDialogOwnerImpl : BookDialogOwner {
    override fun Fragment.showDeleteBookDialog(
        onPositiveButtonClick: (dialog: MaterialDialog) -> Unit,
        onNegativeButtonClick: (dialog: MaterialDialog) -> Unit
    ): MaterialDialog {
        return dialog {
            title(R.string.delete_book_dialog_title)
            message(R.string.delete_book_dialog_message)
            positiveButton(R.string.yes) { onPositiveButtonClick(it) }
            negativeButton(R.string.no) { onNegativeButtonClick(it) }
        }
    }
}