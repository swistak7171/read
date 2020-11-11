package pl.kamilszustak.read.domain.usecase.device

import android.app.Application
import android.telephony.TelephonyManager
import androidx.core.content.getSystemService
import pl.kamilszustak.read.common.util.useOrNull
import pl.kamilszustak.read.domain.access.usecase.device.GetPhoneNumberUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhoneNumberUseCaseImpl @Inject constructor(
    private val application: Application,
) : GetPhoneNumberUseCase {

    override fun invoke(): String? {
        val telephonyManager = application.getSystemService<TelephonyManager>()

        return telephonyManager.useOrNull { manager ->
            val number = manager.line1Number
            if (number != null && number.length > 2) {
                number.substring(2)
            } else {
                null
            }
        }
    }
}