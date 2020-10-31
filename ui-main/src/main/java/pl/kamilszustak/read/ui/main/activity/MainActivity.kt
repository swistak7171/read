package pl.kamilszustak.read.ui.main.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.FirebaseAuth
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.setupActionBarWithNavController
import pl.kamilszustak.read.ui.base.util.setupWithNavController
import pl.kamilszustak.read.ui.base.view.activity.BaseActivity
import pl.kamilszustak.read.ui.main.R
import pl.kamilszustak.read.ui.main.databinding.ActivityMainBinding
import pl.kamilszustak.read.ui.main.di.MainComponent
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject override lateinit var fragmentFactory: FragmentFactory
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject protected lateinit var firebaseAuth: FirebaseAuth

    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private var currentNavController: LiveData<NavController>? = null
    private val navigator: Navigator = Navigator()
    private val component: MainComponent by lazy {
        (application as MainComponent.ComponentProvider).provideMainComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        setupActionBarWithNavController(R.id.mainNavHostFragment)
        onBackPressedDispatcher.addCallback(this) {
            val handled = onSupportNavigateUp()
            if (!handled) {
                this.isEnabled = false
                onBackPressed()
                this.isEnabled = true
            }
        }

        observeViewModel()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState)
        }

        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean =
        currentNavController?.value?.navigateUp() ?: false

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_collection,
            R.navigation.navigation_search,
            R.navigation.navigation_scanner,
            R.navigation.navigation_quotes,
            R.navigation.navigation_profile
        )

        val controller = binding.mainBottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.mainNavHostFragment,
            intent = intent
        )

        controller.observe(this) {
            setupActionBarWithNavController(it)
        }

        currentNavController = controller
    }

    private fun observeViewModel() {
        viewModel.action.observe(this) { action ->
            when (action) {
                is MainAction.ChangeFragmentSelection -> {
                    binding.mainBottomNavigationView.selectedItemId = action.idResource
                }

                MainAction.NavigateToAuthenticationActivity -> {
                    navigator.navigateToAuthenticationActivity()
                }
            }
        }
    }

    private inner class Navigator {
        fun navigateToAuthenticationActivity() {
            finish()
            val intent = Intent().apply {
                setClassName(
                    "pl.kamilszustak.read",
                    "pl.kamilszustak.read.ui.authentication.AuthenticationActivity"
                )
            }

            startActivity(intent)
        }
    }
}