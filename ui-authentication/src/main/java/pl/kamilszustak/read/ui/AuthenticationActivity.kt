package pl.kamilszustak.read.ui

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import dagger.android.AndroidInjection
import pl.kamilszustak.ui.BaseActivity
import javax.inject.Inject

class AuthenticationActivity : BaseActivity(R.layout.activity_authentication) {
    @Inject protected lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        initializeFragmentFactory()
        super.onCreate(savedInstanceState)
    }

    private fun initializeFragmentFactory() {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}