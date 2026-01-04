package br.com.findnumbers

import android.app.Application
import br.com.findnumbers.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                AppModule.module
            )
        }
        super.onCreate()
    }
}