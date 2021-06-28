/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Unit.kt] created by Ji Sungbin on 21. 6. 27. 오전 2:05.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun doDelay(ms: Long, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action() }, ms)
}

fun toast(context: Context, message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, length).show()
}