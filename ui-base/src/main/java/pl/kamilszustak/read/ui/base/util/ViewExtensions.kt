package pl.kamilszustak.read.ui.base.util

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.PopupMenu
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.core.content.getSystemService

fun View.showKeyboard() {
    post {
        requestFocus()
        val inputMethodManager = context.getSystemService<InputMethodManager>()
        inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideKeyboard() {
    post {
        val inputMethodManager = context.getSystemService<InputMethodManager>()
        inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
    }
}

inline fun View.setVisible() {
    this.visibility = View.VISIBLE
}

inline fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

inline fun View.setGone() {
    this.visibility = View.GONE
}

fun TextView.setFormattedText(@StringRes textResource: Int, vararg arguments: Any) {
    this.text = this.context.resources.getString(textResource, *arguments)
}

fun popupMenu(view: View, @MenuRes menuResource: Int, initialization: PopupMenu.() -> Unit) {
    PopupMenu(view.context, view).apply {
        this.inflate(menuResource)
        this.initialization()
    }.show()
}

fun View.setOnDoubleClickListener(action: () -> Unit) {
    val listener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            action()
            return true
        }
    }

    val gestureDetector = GestureDetector(this.context, listener)
    this.setOnTouchListener { v, event ->
        gestureDetector.onTouchEvent(event)
    }
}