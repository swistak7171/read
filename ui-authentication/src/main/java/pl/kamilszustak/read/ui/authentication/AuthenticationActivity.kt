package pl.kamilszustak.read.ui.authentication

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import pl.kamilszustak.read.ui.base.BaseActivity
import pl.kamilszustak.read.ui.authentication.di.component.AuthenticationComponent
import pl.kamilszustak.read.ui.authentication.di.component.AuthenticationComponentProvider
import javax.inject.Inject

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {
    @Inject protected lateinit var fragmentFactory: FragmentFactory
    private val authenticationComponent: AuthenticationComponent by lazy {
        (application as AuthenticationComponentProvider).provideAuthenticationComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        authenticationComponent.inject(this)
        initializeFragmentFactory()
        super.onCreate(savedInstanceState)
    }

    private fun initializeFragmentFactory() {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}