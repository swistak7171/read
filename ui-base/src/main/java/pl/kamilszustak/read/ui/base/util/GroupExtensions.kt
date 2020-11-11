package pl.kamilszustak.read.ui.base.util

import android.view.View
import androidx.constraintlayout.widget.Group

inline fun Group.setVisible() {
    visibility = View.VISIBLE
}

inline fun Group.setInvisible() {
    visibility = View.INVISIBLE
}

inline fun Group.setGone() {
    visibility = View.GONE
}