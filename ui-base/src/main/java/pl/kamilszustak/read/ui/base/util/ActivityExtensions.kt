package pl.kamilszustak.read.ui.base.util

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
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

fun Activity.navigateUp(navHostFragmentId: Int): Boolean =
    this.findNavController(navHostFragmentId).navigateUp()

fun AppCompatActivity.setupActionBarWithNavController(@IdRes fragmentContainerResId: Int) {
    val navHostFragment = this.supportFragmentManager.findFragmentById(fragmentContainerResId) as NavHostFragment
    val navController = navHostFragment.navController
    setupActionBarWithNavController(navController)
}