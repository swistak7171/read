package pl.kamilszustak.read.common.util

import android.content.Context
import android.content.pm.ShortcutInfo
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N_MR1)
inline fun shortcutInfoBuilder(context: Context, id: String, builder: ShortcutInfo.Builder.() -> Unit): ShortcutInfo =
    ShortcutInfo.Builder(context, id)
        .apply { builder() }
        .build()