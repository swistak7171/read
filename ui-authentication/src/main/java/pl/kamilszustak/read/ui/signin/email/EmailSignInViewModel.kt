package pl.kamilszustak.read.ui.signin.email

import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.ui.BaseViewModel
import javax.inject.Inject

class EmailSignInViewModel @Inject constructor() : BaseViewModel() {
    val userEmailAddress: UniqueLiveData<String> = UniqueLiveData()
    val userPassword: UniqueLiveData<String> = UniqueLiveData()
    val retypedUserPassword: UniqueLiveData<String> = UniqueLiveData()
}