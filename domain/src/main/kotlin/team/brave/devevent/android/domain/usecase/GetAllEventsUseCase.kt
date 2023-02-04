/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [GetAllEventsUseCase.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:00
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.domain.usecase

import javax.inject.Inject
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.repository.EventRepository

class GetAllEventsUseCase @Inject constructor(private val repository: EventRepository) {
    suspend operator fun invoke(): Result<List<Event>> {
        return runCatching {
            repository.getAllEvents()
        }
    }
}
