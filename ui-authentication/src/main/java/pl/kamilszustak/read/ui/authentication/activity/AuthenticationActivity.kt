package pl.kamilszustak.read.ui.authentication.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.startActivity
import pl.kamilszustak.read.ui.authentication.R
import pl.kamilszustak.read.ui.authentication.di.AuthenticationComponent
import pl.kamilszustak.read.ui.base.view.activity.BaseActivity
import pl.kamilszustak.read.ui.main.activity.MainActivity
import javax.inject.Inject

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {
    @Inject override lateinit var fragmentFactory: FragmentFactory
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AuthenticationViewModel by viewModels { viewModelFactory }
    private val navigator: Navigator = Navigator()
    private val authenticationComponent: AuthenticationComponent by lazy {
        (application as AuthenticationComponent.ComponentProvider).provideAuthenticationComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        authenticationComponent.inject(this)
        super.onCreate(savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.action.observe(this) { action ->
            when (action) {
                AuthenticationAction.NavigateToMainActivity -> navigator.navigateToMainActivity()
            }
        }
    }

    private inner class Navigator {
        fun navigateToMainActivity() {
            finish()
            startActivity<MainActivity>()
        }
    }
}