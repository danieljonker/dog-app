package nz.co.jonker.dogsapp.di

import android.app.Application
import nz.co.jonker.breedlist.di.breedListModule
import nz.co.jonker.breedview.di.breedViewModule
import nz.co.jonker.networking.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun initialiseKoin(application: Application) {
    startKoin {
        androidContext(application)
        androidLogger()
        modules(
            listOf(
                breedListModule,
                networkingModule,
                breedViewModule,
            )
        )
    }
}
