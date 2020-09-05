package pl.kamilszustak.ui.util

import com.afollestad.assent.AssentResult
import com.afollestad.assent.Permission

fun AssentResult.getStateOf(permission: Permission): PermissionState {
    val isGranted = this.isAllGranted(permission)
    return if (isGranted) {
        PermissionState.GRANTED
    } else {
        val permanentlyDeniedPermissions = this.permanentlyDenied()
        if (permission in permanentlyDeniedPermissions) {
            PermissionState.PERMANENTLY_DENIED
        } else {
            PermissionState.DENIED
        }
    }
}

enum class PermissionState {
    UNKNOWN,
    GRANTED,
    DENIED,
    PERMANENTLY_DENIED
}