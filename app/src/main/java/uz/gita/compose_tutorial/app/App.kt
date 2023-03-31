package uz.gita.compose_tutorial.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import uz.gita.compose_tutorial.di.viewModelInject

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelInject)
        }
    }
}