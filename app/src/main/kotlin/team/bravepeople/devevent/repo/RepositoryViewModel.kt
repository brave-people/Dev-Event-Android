/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [RepositoryViewModel.kt] created by Ji Sungbin on 21. 6. 25. 오전 12:04.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.repo

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import team.bravepeople.devevent.activity.main.event.EventViewModel
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.activity.main.event.database.EventEntity
import team.bravepeople.devevent.util.NetworkUtil
import team.bravepeople.devevent.util.extension.parseOrNull

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val client: GithubService,
    private val database: EventDatabase
) : ViewModel() {

    private val databaseDao = database.dao()
    private val eventVm = EventViewModel.instance
    private val eventEntities: List<EventEntity> get() = eventVm.eventEntities

    fun loadEvents(
        context: Context,
        endAction: suspend () -> Unit,
        networkNotAvailableAction: () -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
        if (eventEntities.isNotEmpty()) {
            endAction()
        } else {
            eventVm.addEvents(databaseDao.getEvents())
            if (eventEntities.isEmpty()) {
                if (NetworkUtil.isNetworkAvailable(context)) {
                    client.getEvents().await().use { response ->
                        runCatching { parseAndSave(response.string()) }
                    }
                    endAction()
                } else {
                    networkNotAvailableAction()
                }
            } else {
                endAction()
            }
        }
    }

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        if (databaseDao.getEvents().isEmpty()) {
            databaseDao.insertAll(eventEntities)
        } else {
            eventEntities.filterNot { event -> databaseDao.getEvents().contains(event) }
            databaseDao.updateAll(eventEntities)
        }
    }

    private fun parseAndSave(value: String) {
        fun String?.polish() =
            if (this != null) replaceFirst("-", "").replaceFirst(":", "")
                .replace("`", "").replace(" ", "").split("\n")[0].trim()
            else null

        val data = value.split("## Dev Event만의 특별함")[1].split("---------------")[0]
        data.split("##").forEachIndexed { eventsIndex, events ->
            if (eventsIndex > 0) {
                val headerDate = events.split("\n")[0].trim()
                events.split("- __").forEachIndexed { eventIndex, event ->
                    if (eventIndex > 0) {
                        event.run {
                            var site: String? = null
                            val name: String

                            if (contains("http")) {
                                name = "__$this".split("__[")[1].split("](http")[0]
                                site = "http" + split("(http")[1].split(")")[0]
                            } else {
                                name = split("__")[0]
                            }

                            val category = parseOrNull("- 분류").polish()
                            val joinDate = (parseOrNull("- 신청") ?: parseOrNull("- 모집")).polish()
                            val startDate = parseOrNull("- 일시").polish()
                            val owner = parseOrNull("- 주최").polish()

                            val eventEntity = EventEntity(
                                site = site,
                                name = name,
                                category = category,
                                headerDate = headerDate,
                                joinDate = joinDate,
                                startDate = startDate,
                                owner = owner
                            )

                            eventVm.addEvent(eventEntity)
                        }
                    }
                }
            }
        }
    }
}