package pl.kamilszustak.read.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.model.domain.User
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getUser: GetUserUseCase,
    private val signOut: SignOutUseCase,
) : BaseViewModel<ProfileEvent, ProfileAction>() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _user.value = getUser().also {
                Timber.i(it.toString())
            }
        }
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnEditButtonClicked -> {
                _action.value = ProfileAction.NavigateToProfileEditFragment
            }

            ProfileEvent.OnSignOutButtonClicked -> {
                signOut()
            }
        }
    }
}