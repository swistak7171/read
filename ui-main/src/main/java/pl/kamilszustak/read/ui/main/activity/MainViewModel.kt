package pl.kamilszustak.read.ui.main.activity

import android.app.Application
import android.content.Intent
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.core.content.getSystemService
import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.common.util.shortcutInfoBuilder
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val application: Application,
    private val firebaseAuth: FirebaseAuth,
) : BaseViewModel<MainEvent, MainAction>(), FirebaseAuth.AuthStateListener {

    init {
        _action.value = MainAction.ChangeFragmentSelection(MainFragmentType.COLLECTION_FRAGMENT.itemId)
        firebaseAuth.addAuthStateListener(this)
    }

    override fun onCleared() {
        firebaseAuth.removeAuthStateListener(this)
    }

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        if (auth.currentUser != null) {
            addShortcuts()
        } else {
            deleteShortcuts()
            _action.value = MainAction.NavigateToAuthenticationActivity
        }
    }

    override fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnFragmentSelectionChanged -> {
                _action.value = MainAction.ChangeFragmentSelection(event.type.itemId)
            }
        }
    }

    private fun addShortcuts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            return
        }

        val shortcutManager = application.getSystemService<ShortcutManager>() ?: return
        shortcutManager.dynamicShortcuts = listOf(
            shortcutInfoBuilder(application, "scan") {
                setIcon(Icon.createWithResource(application, R.drawable.icon_capture))
                setShortLabel("Scan")
                setIntent(Intent(Intent.ACTION_VIEW))
            },
        )
    }

    private fun deleteShortcuts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N_MR1) {
            return
        }

        val shortcutManager = application.getSystemService<ShortcutManager>() ?: return
        shortcutManager.removeAllDynamicShortcuts()
    }
}