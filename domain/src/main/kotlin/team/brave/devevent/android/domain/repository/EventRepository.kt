/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventRepository.kt] created by Ji Sungbin on 23. 1. 1. 오후 2:57
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.domain.repository

import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.model.Tag

/**
 * 이벤트와 관련된 데이터를 제공하는 저장소
 */
interface EventRepository {
    /**
     * 현재 조회 가능한 모든 [이벤트][Event]를 불러옵니다.
     */
    suspend fun getAllEvents(): List<Event>
}
