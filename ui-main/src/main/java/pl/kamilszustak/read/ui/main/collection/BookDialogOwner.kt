package pl.kamilszustak.read.ui.main.collection

import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog

interface BookDialogOwner {
    fun Fragment.showDeleteBookDialog(
        onPositiveButtonClick: ((dialog: MaterialDialog) -> Unit) = {},
        onNegativeButtonClick: ((dialog: MaterialDialog) -> Unit) = {},
    ): MaterialDialog
}