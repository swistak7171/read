@file:Suppress("NOTHING_TO_INLINE", "unused")

package pl.kamilszustak.read.ui.base.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty

inline fun Context.normalToast(@StringRes textResource: Int) {
    Toasty.normal(this, textResource)
        .show()
}

inline fun Context.normalToast(text: CharSequence) {
    Toasty.normal(this, text)
        .show()
}

inline fun Context.longNormalToast(@StringRes textResource: Int) {
    Toasty.normal(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longNormalToast(text: CharSequence) {
    Toasty.normal(this, text, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.warningToast(@StringRes textResource: Int) {
    Toasty.warning(this, textResource)
        .show()
}

inline fun Context.warningToast(text: CharSequence) {
    Toasty.warning(this, text)
        .show()
}

inline fun Context.longWarningToast(@StringRes textResource: Int) {
    Toasty.warning(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longWarningToast(text: CharSequence) {
    Toasty.warning(this, text, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.infoToast(@StringRes textResource: Int) {
    Toasty.info(this, textResource)
        .show()
}

inline fun Context.infoToast(text: CharSequence) {
    Toasty.info(this, text)
        .show()
}

inline fun Context.longInfoToast(@StringRes textResource: Int) {
    Toasty.info(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longInfoToast(text: CharSequence) {
    Toasty.info(this, text, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.successToast(@StringRes textResource: Int) {
    Toasty.success(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.successToast(text: CharSequence) {
    Toasty.success(this, text, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longSuccessToast(@StringRes textResource: Int) {
    Toasty.success(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longSuccessToast(text: CharSequence) {
    Toasty.success(this, text, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.errorToast(@StringRes textResource: Int) {
    Toasty.error(this, textResource)
        .show()
}

inline fun Context.errorToast(text: CharSequence) {
    Toasty.error(this, text)
        .show()
}

inline fun Context.longErrorToast(@StringRes textResource: Int) {
    Toasty.error(this, textResource, Toasty.LENGTH_LONG)
        .show()
}

inline fun Context.longErrorToast(text: CharSequence) {
    Toasty.error(this, text, Toasty.LENGTH_LONG)
        .show()
}

inline fun Fragment.normalToast(@StringRes textResource: Int) {
    this.requireContext().normalToast(textResource)
}

inline fun Fragment.normalToast(text: CharSequence) {
    this.requireContext().normalToast(text)
}

inline fun Fragment.longNormalToast(@StringRes textResource: Int) {
    this.requireContext().longNormalToast(textResource)
}

inline fun Fragment.longNormalToast(text: CharSequence) {
    this.requireContext().longNormalToast(text)
}

inline fun Fragment.warningToast(@StringRes textResource: Int) {
    this.requireContext().warningToast(textResource)
}

inline fun Fragment.warningToast(text: CharSequence) {
    this.requireContext().warningToast(text)
}

inline fun Fragment.longWarningToast(@StringRes textResource: Int) {
    this.requireContext().longWarningToast(textResource)
}

inline fun Fragment.longWarningToast(text: CharSequence) {
    this.requireContext().longWarningToast(text)
}

inline fun Fragment.infoToast(@StringRes textResource: Int) {
    this.requireContext().infoToast(textResource)
}

inline fun Fragment.infoToast(text: CharSequence) {
    this.requireContext().infoToast(text)
}

inline fun Fragment.longInfoToast(@StringRes textResource: Int) {
    this.requireContext().longInfoToast(textResource)
}

inline fun Fragment.longInfoToast(text: CharSequence) {
    this.requireContext().longInfoToast(text)
}

inline fun Fragment.successToast(@StringRes textResource: Int) {
    this.requireContext().successToast(textResource)
}

inline fun Fragment.successToast(text: CharSequence) {
    this.requireContext().successToast(text)
}

inline fun Fragment.longSuccessToast(@StringRes textResource: Int) {
    this.requireContext().longSuccessToast(textResource)
}

inline fun Fragment.longSuccessToast(text: CharSequence) {
    this.requireContext().longSuccessToast(text)
}

inline fun Fragment.errorToast(@StringRes textResource: Int) {
    this.requireContext().errorToast(textResource)
}

inline fun Fragment.errorToast(text: CharSequence) {
    this.requireContext().errorToast(text)
}

inline fun Fragment.longErrorToast(@StringRes textResource: Int) {
    this.requireContext().longErrorToast(textResource)
}

inline fun Fragment.longErrorToast(text: CharSequence) {
    this.requireContext().longErrorToast(text)
}