package pl.kamilszustak.read.ui.main.profile.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.user.EditUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.model.domain.user.ProfileDetails
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import timber.log.Timber
import javax.inject.Inject

class ProfileEditViewModel @Inject constructor(
    private val getUser: GetUserUseCase,
    private val editUser: EditUserUseCase,
    private val validator: FormValidator,
) : BaseViewModel<ProfileEditEvent, ProfileEditAction>() {

    val userName: MutableLiveData<String> = UniqueLiveData()
    val userEmailAddress: MutableLiveData<String> = UniqueLiveData()

    init {
        val user = getUser()
        if (user.name != null) {
            userName.value = user.name
        }

        if (user.emailAddress != null) {
            userEmailAddress.value = user.emailAddress
        }
    }

    override fun handleEvent(event: ProfileEditEvent) {
        when (event) {
            ProfileEditEvent.OnSaveButtonClicked -> handleSaveButtonClick()
        }
    }

    private fun handleSaveButtonClick() {
        val name = userName.value
        val emailAddress = userEmailAddress.value

        if (emailAddress != null && !validator.validateEmailAddress(emailAddress)) {
            _action.value = ProfileEditAction.Error(R.string.invalid_email_address)
            return
        }

        val details = ProfileDetails(
            name = name,
            emailAddress = emailAddress
        )

        viewModelScope.launch(Dispatchers.Main) {
            editUser(details)
                .onSuccess {
                    with(_action) {
                        value = ProfileEditAction.ProfileEdited
                        value = ProfileEditAction.NavigateUp
                    }
                }.onFailure {
                    Timber.e(it)
                    _action.value = ProfileEditAction.Error(R.string.profil_edit_error_message)
                }
        }
    }
}