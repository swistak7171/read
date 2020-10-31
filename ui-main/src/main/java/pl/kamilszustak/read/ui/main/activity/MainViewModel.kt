package pl.kamilszustak.read.ui.main.activity

import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
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
        if (auth.currentUser == null) {
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
}