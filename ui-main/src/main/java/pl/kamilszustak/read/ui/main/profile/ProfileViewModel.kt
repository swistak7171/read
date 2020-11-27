package pl.kamilszustak.read.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveMonthlyReadingStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.user.ObserveUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.model.domain.user.User
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import java.util.*
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val observeUser: ObserveUserUseCase,
    private val observeMonthlyReadingStatistics: ObserveMonthlyReadingStatisticsUseCase,
    private val signOut: SignOutUseCase,
) : BaseViewModel<ProfileEvent, ProfileAction>() {

    val user: LiveData<User> = observeUser()
        .asLiveData(viewModelScope.coroutineContext)

    val monthlyStatistics: LiveData<Map<String, Int>>

    init {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        monthlyStatistics = observeMonthlyReadingStatistics(year, month)
            .asLiveData(viewModelScope.coroutineContext)
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