package pl.kamilszustak.read.ui.base.util

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
    factory: ViewModelProvider.Factory
): Lazy<VM> = this.viewModels { factory }

inline fun Activity.dialog(block: MaterialDialog.() -> Unit): MaterialDialog {
    return MaterialDialog(this).show {
        block()
        if (this@dialog is LifecycleOwner) {
            lifecycleOwner(this@dialog)
        }
    }
}