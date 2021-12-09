package pan.alexander.timer

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level
import pan.alexander.timer.di.appModules

class App : Application() {

    companion object {
        const val LOG_TAG = "timer"
    }

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(appModules)
        }
    }

}
