package pl.kamilszustak.read.ui.main.profile

import androidx.lifecycle.*
import kotlinx.coroutines.flow.map
import pl.kamilszustak.read.common.date.Month
import pl.kamilszustak.read.common.date.SimpleDate
import pl.kamilszustak.read.common.lifecycle.UniqueLiveData
import pl.kamilszustak.read.domain.access.usecase.statistics.ObserveMonthlyReadingStatisticsUseCase
import pl.kamilszustak.read.domain.access.usecase.user.ObserveUserUseCase
import pl.kamilszustak.read.domain.access.usecase.user.SignOutUseCase
import pl.kamilszustak.read.model.domain.user.User
import pl.kamilszustak.read.ui.base.view.viewmodel.BaseViewModel
import pl.kamilszustak.read.ui.main.R
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val observeUser: ObserveUserUseCase,
    private val observeMonthlyReadingStatistics: ObserveMonthlyReadingStatisticsUseCase,
    private val signOut: SignOutUseCase,
) : BaseViewModel<ProfileEvent, ProfileAction>() {

    val user: LiveData<User> = observeUser()
        .asLiveData(viewModelScope.coroutineContext)

    val monthlyStatistics: LiveData<Map<String, Int>>
    private val currentMonth: MutableLiveData<Month> = UniqueLiveData()
    val currentMonthName: LiveData<Int>
        get() = currentMonth.map { month ->
            month?.nameResourceId ?: R.string.empty_placeholder
        }

    init {
        val date = SimpleDate.current()
        monthlyStatistics = observeMonthlyReadingStatistics(date)
            .map { map ->
                map.mapKeys { mapEntry ->
                    mapEntry.key.day.toString()
                }
            }
            .asLiveData(viewModelScope.coroutineContext)

        currentMonth.value = Month.ofNumber(date.month)
    }

    override fun handleEvent(event: ProfileEvent) {
        when (event) {
            ProfileEvent.OnEditButtonClicked -> {
                _action.value = ProfileAction.NavigateToProfileEditFragment
            }

            ProfileEvent.OnMoreStatisticsButtonClicked -> {
                _action.value = ProfileAction.NavigateToStatisticsFragment
            }

            ProfileEvent.OnSignOutButtonClicked -> {
                signOut()
            }
        }
    }
}