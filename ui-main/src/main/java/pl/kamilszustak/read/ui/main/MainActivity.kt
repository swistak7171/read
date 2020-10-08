package pl.kamilszustak.read.ui.main

import android.os.Bundle
import androidx.activity.addCallback
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import pl.kamilszustak.read.ui.base.binding.viewBinding
import pl.kamilszustak.read.ui.base.util.setupActionBarWithNavController
import pl.kamilszustak.read.ui.base.util.setupWithNavController
import pl.kamilszustak.read.ui.base.view.activity.BaseActivity
import pl.kamilszustak.read.ui.main.databinding.ActivityMainBinding
import pl.kamilszustak.read.ui.main.di.MainComponent
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject override lateinit var fragmentFactory: FragmentFactory
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private var currentNavController: LiveData<NavController>? = null
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
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState)
        }

        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_collection,
            R.navigation.navigation_search,
            R.navigation.navigation_discover,
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

    override fun onSupportNavigateUp(): Boolean =
        currentNavController?.value?.navigateUp() ?: false
}