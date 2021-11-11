/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Result.kt] created by Ji Sungbin on 21. 11. 11. 오후 5:13
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

inline fun <T> Result<T>.doWhen(
    onSuccess: (result: T) -> Unit,
    onFailure: (throwable: Throwable) -> Unit
) {
    if (isSuccess) {
        onSuccess(getOrNull()!!)
    } else {
        onFailure(exceptionOrNull()!!)
    }
}
