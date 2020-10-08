package pl.kamilszustak.read.ui.base.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory

abstract class BaseActivity : AppCompatActivity {
    constructor() : super()
    constructor(@LayoutRes layoutResourceId: Int) : super(layoutResourceId)

    protected abstract val fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState, persistentState)
    }
}