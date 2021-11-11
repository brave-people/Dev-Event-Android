/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [toast.kt] created by Ji Sungbin on 21. 6. 28. 오후 8:46.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    toast(context = applicationContext, message = message, length = length)
}

fun Activity.errorToast(exception: Exception) {
    errorToast(context = applicationContext, exception = exception)
}

fun toast(context: Context, message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, length).show()
}

fun errorToast(context: Context, exception: Exception) {
    toast(
        context = context,
        message = "에러가 발생했어요.\n\n${exception.message}",
        length = Toast.LENGTH_LONG
    )
}
