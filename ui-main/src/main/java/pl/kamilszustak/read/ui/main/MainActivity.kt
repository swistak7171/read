package pl.kamilszustak.read.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentFactory
import pl.kamilszustak.read.ui.base.view.activity.BaseActivity
import pl.kamilszustak.read.ui.main.di.MainComponent
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject protected override lateinit var fragmentFactory: FragmentFactory
    val component: MainComponent by lazy {
        (application as MainComponent.ComponentProvider).provideMainComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState, persistentState)
    }
}