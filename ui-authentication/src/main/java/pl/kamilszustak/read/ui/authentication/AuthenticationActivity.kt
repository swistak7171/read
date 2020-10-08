package pl.kamilszustak.read.ui.authentication

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import pl.kamilszustak.read.ui.authentication.di.AuthenticationComponent
import pl.kamilszustak.read.ui.base.view.activity.BaseActivity
import javax.inject.Inject

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {
    @Inject override lateinit var fragmentFactory: FragmentFactory
    private val authenticationComponent: AuthenticationComponent by lazy {
        (application as AuthenticationComponent.ComponentProvider).provideAuthenticationComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        authenticationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}