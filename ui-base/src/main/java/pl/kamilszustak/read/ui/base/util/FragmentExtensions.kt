package pl.kamilszustak.read.ui.base.util

import android.net.Uri
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import pl.kamilszustak.read.common.util.tryOrNull

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    factory: ViewModelProvider.Factory
): Lazy<VM> = this.viewModels { factory }

@MainThread
inline fun <reified VM : ViewModel> Fragment.activityViewModels(
    factory: ViewModelProvider.Factory
): Lazy<VM> = this.activityViewModels { factory }

@MainThread
inline fun <reified VM : ViewModel> Fragment.navGraphViewModels(
    @IdRes navGraphId: Int,
    factory: ViewModelProvider.Factory
): Lazy<VM> = this.navGraphViewModels(navGraphId) { factory }

inline val Fragment.navController: NavController?
    get() = tryOrNull { findNavController() }

fun Fragment.navigate(directions: NavDirections) {
    val controller = navController ?: return
    val isSafe = isNavigationSafe(controller, directions)
    if (isSafe) {
        controller.navigate(directions)
    }
}

fun Fragment.navigate(deepLink: Uri) {
    navController?.navigate(deepLink)
}

fun Fragment.navigate(directions: NavDirections, navOptions: NavOptions) {
    val navController = navController ?: return
    val isSafe = isNavigationSafe(navController, directions)
    if (isSafe) {
        navController.navigate(directions, navOptions)
    }
}

fun Fragment.navigate(deepLink: Uri, options: NavOptions) {
    navController?.navigate(deepLink, options)
}

fun Fragment.navigate(directions: NavDirections, extras: Navigator.Extras) {
    val navController = navController ?: return
    val isSafe = isNavigationSafe(navController, directions)
    if (isSafe) {
        navController.navigate(directions, extras)
    }
}

fun Fragment.navigate(deepLink: Uri, options: NavOptions, extras: Navigator.Extras) {
    navController?.navigate(deepLink, options, extras)
}

fun Fragment.navigate(
    destinationId: Int,
    args: Bundle,
    options: NavOptions,
    extras: Navigator.Extras
) {
    val navController = navController ?: return
    val isSafe = isNavigationSafe(navController, destinationId)
    if (isSafe) {
        navController.navigate(destinationId, args, options, extras)
    }
}

private fun isNavigationSafe(navController: NavController, directions: NavDirections): Boolean =
    isNavigationSafe(navController, directions.actionId)

private fun isNavigationSafe(navController: NavController, actionId: Int): Boolean =
    navController.currentDestination?.getAction(actionId) != null

fun Fragment.navigateUp(): Boolean = navController?.navigateUp() ?: false

inline fun Fragment.dialog(crossinline block: MaterialDialog.() -> Unit): MaterialDialog {
    return MaterialDialog(requireContext()).show {
        block()
        lifecycleOwner(this@dialog.viewLifecycleOwner)
    }
}