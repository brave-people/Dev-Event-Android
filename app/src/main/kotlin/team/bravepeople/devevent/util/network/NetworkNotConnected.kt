/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [NetworkNotConnected.kt] created by Ji Sungbin on 21. 7. 2. 오전 4:14.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.network

class NetworkNotConnected : Exception() {
    override val message: String
        get() = "인터넷 연결이 필요합니다."
}