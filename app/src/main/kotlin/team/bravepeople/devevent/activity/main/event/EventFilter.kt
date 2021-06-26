/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventFilter.kt] created by Ji Sungbin on 21. 6. 24. 오전 12:29.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event

sealed class EventFilter {
    object None : EventFilter()
    object Favorite : EventFilter()
}
