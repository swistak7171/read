package pl.kamilszustak.read.ui.main.di.module

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pl.kamilszustak.read.ui.base.di.key.FragmentKey
import pl.kamilszustak.read.ui.main.book.edit.BookEditFragment
import pl.kamilszustak.read.ui.main.collection.CollectionFragment
import pl.kamilszustak.read.ui.main.discover.DiscoverFragment
import pl.kamilszustak.read.ui.main.profile.ProfileFragment
import pl.kamilszustak.read.ui.main.quotes.QuotesFragment
import pl.kamilszustak.read.ui.main.search.SearchFragment

@Module
interface MainFragmentModule {
    @Binds
    @IntoMap
    @FragmentKey(CollectionFragment::class)
    fun bindCollectionFragment(fragment: CollectionFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SearchFragment::class)
    fun bindSearchFragment(fragment: SearchFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DiscoverFragment::class)
    fun bindDiscoverFragment(fragment: DiscoverFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(QuotesFragment::class)
    fun bindQuotesFragment(fragment: QuotesFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ProfileFragment::class)
    fun bindProfileFragment(fragment: ProfileFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(BookEditFragment::class)
    fun bindBookEditFragment(fragment: BookEditFragment): Fragment
}