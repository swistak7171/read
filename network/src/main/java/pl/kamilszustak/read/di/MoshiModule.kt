package pl.kamilszustak.read.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.addAdapter
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class MoshiModule {
    @OptIn(ExperimentalStdlibApi::class)
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addAdapter<Date>(Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }
}