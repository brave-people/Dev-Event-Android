/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [DevEventAndroid.kt] created by Ji Sungbin on 21. 6. 20. 오후 11:55.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.jisungbin.erratum.Erratum
import io.github.jisungbin.logeukes.Logeukes

@HiltAndroidApp
class DevEventAndroid : Application() {
    override fun onCreate() {
        super.onCreate()
        Erratum.setup(this)
        if (BuildConfig.DEBUG) {
            Logeukes.setup()
        }
    }
}
