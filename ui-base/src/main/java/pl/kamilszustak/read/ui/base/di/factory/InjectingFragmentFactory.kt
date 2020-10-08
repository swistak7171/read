package pl.kamilszustak.read.ui.base.di.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class InjectingFragmentFactory @Inject constructor(
    private val fragments: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        val creator = fragments[fragmentClass]
            ?: return super.instantiate(classLoader, className).also {
                Timber.w("No provider found for class: $className. Using default constructor")
            }

        return creator.get()
    }
}