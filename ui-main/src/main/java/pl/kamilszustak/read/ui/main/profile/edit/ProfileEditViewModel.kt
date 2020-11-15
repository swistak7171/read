package pl.kamilszustak.read.ui.main.profile.edit

import androidx.lifecycle.MutableLiveData
import pl.kamilszustak.read.common.FormValidator
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.user.GetUserUseCase
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import javax.inject.Inject

class ProfileEditViewModel @Inject constructor(
    private val getUser: GetUserUseCase,
    private val validator: FormValidator,
) : BaseViewModel<ProfileEditEvent, ProfileEditAction>() {

    val userName: MutableLiveData<String> = UniqueLiveData()
    val userEmailAddress: MutableLiveData<String> = UniqueLiveData()
    val userPhoneNumber: MutableLiveData<String> = UniqueLiveData()

    init {
        val user = getUser()
        if (user.name != null) {
            userName.value = user.name
        }

        if (user.emailAddress != null) {
            userEmailAddress.value = user.emailAddress
        }

        if (user.phoneNumber != null) {
            userPhoneNumber.value = user.phoneNumber
        }
    }

    override fun handleEvent(event: ProfileEditEvent) {
        when (event) {
            ProfileEditEvent.OnSaveButtonClicked -> handleSaveButtonClick()
        }
    }

    private fun handleSaveButtonClick() {
        val name = userName.value
        val email = userEmailAddress.value
        val phoneNumber = userPhoneNumber.value
    }
}