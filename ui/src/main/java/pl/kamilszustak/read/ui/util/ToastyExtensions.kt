@file:Suppress("NOTHING_TO_INLINE", "unused")

package pl.kamilszustak.read.ui.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty

inline fun Context.normalToast(@StringRes textResource: Int) {
    Toasty.normal(this, textResource)
        .show()
}

inline fun Context.longNormalToast(@StringRes textResource: Int) {
    Toasty.normal(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.warningToast(@StringRes textResource: Int) {
    Toasty.warning(this, textResource)
        .show()
}

inline fun Context.longWarningToast(@StringRes textResource: Int) {
    Toasty.warning(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.infoToast(@StringRes textResource: Int) {
    Toasty.info(this, textResource)
        .show()
}

inline fun Context.longInfoToast(@StringRes textResource: Int) {
    Toasty.info(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.successToast(@StringRes textResource: Int) {
    Toasty.success(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longSuccessToast(@StringRes textResource: Int) {
    Toasty.success(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.errorToast(@StringRes textResource: Int) {
    Toasty.error(this, textResource)
        .show()
}

inline fun Context.longErrorToast(@StringRes textResource: Int) {
    Toasty.error(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Fragment.normalToast(@StringRes textResource: Int) {
    this.requireActivity().normalToast(textResource)
}

inline fun Fragment.longNormalToast(@StringRes textResource: Int) {
    this.requireActivity().longNormalToast(textResource)
}

inline fun Fragment.warningToast(@StringRes textResource: Int) {
    this.requireActivity().warningToast(textResource)
}

inline fun Fragment.longWarningToast(@StringRes textResource: Int) {
    this.requireActivity().longWarningToast(textResource)
}

inline fun Fragment.infoToast(@StringRes textResource: Int) {
    this.requireActivity().infoToast(textResource)
}

inline fun Fragment.longInfoToast(@StringRes textResource: Int) {
    this.requireActivity().longInfoToast(textResource)
}

inline fun Fragment.successToast(@StringRes textResource: Int) {
    this.requireActivity().successToast(textResource)
}

inline fun Fragment.longSuccessToast(@StringRes textResource: Int) {
    this.requireActivity().longSuccessToast(textResource)
}

inline fun Fragment.errorToast(@StringRes textResource: Int) {
    this.requireActivity().errorToast(textResource)
}

inline fun Fragment.longErrorToast(@StringRes textResource: Int) {
    this.requireActivity().longErrorToast(textResource)
}