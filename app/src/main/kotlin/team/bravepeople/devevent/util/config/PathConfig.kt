/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [PathManager.kt] created by Ji Sungbin on 21. 6. 28. 오후 9:36.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.config

object PathConfig {
    const val DatabaseSaveTime = "events-database-save-time"
    const val NewEventNotification = "new-event-notification"
    const val AutoEventReload = "events-reload-auto"
    const val EventKeywordAlarm = "events-keyword-alarm"

    val TagColor = { tag: String -> "tag-color-for-$tag" }
}