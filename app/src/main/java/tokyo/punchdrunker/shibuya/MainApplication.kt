package tokyo.punchdrunker.shibuya

import android.app.Application
import timber.log.Timber

/**
 * Created by punchdrunker on 2017/09/30.
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}