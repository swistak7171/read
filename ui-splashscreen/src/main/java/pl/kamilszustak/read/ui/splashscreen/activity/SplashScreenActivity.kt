package pl.kamilszustak.read.ui.splashscreen.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.jetbrains.anko.startActivity
import pl.kamilszustak.read.ui.authentication.activity.AuthenticationActivity
import pl.kamilszustak.read.ui.main.activity.MainActivity
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SplashScreenViewModel by viewModels { viewModelFactory }
    private val navigator: Navigator = Navigator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.action.observe(this) { action ->
            when (action) {
                SplashScreenAction.NavigateToAuthenticationActivity -> navigator.navigateToAuthenticationActivity()
                SplashScreenAction.NavigateToMainActivity -> navigator.navigateToMainActivity()
            }
        }
    }

    private inner class Navigator {
        fun navigateToAuthenticationActivity() {
            startActivity<AuthenticationActivity>()
            finish()
        }

        fun navigateToMainActivity() {
            startActivity<MainActivity>()
            finish()
        }
    }
}