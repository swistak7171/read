package pl.kamilszustak.read.ui.activity.authentication

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import dagger.android.AndroidInjection
import pl.kamilszustak.read.ui.R
import pl.kamilszustak.read.ui.base.BaseActivity
import javax.inject.Inject

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {
    @Inject protected lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeFragmentFactory()
        super.onCreate(savedInstanceState)
    }

    private fun initializeFragmentFactory() {
        AndroidInjection.inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}