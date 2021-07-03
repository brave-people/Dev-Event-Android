/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventReceiver.kt] created by Ji Sungbin on 21. 6. 28. 오후 9:54.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.activity.main.event.repo.EventRepo
import team.bravepeople.devevent.activity.main.event.repo.EventRepoResult
import team.bravepeople.devevent.util.Data
import team.bravepeople.devevent.util.NotificationUtil
import team.bravepeople.devevent.util.config.PathConfig

@AndroidEntryPoint
class EventReloadReceiver : BroadcastReceiver() {

    @Inject
    lateinit var eventRepo: EventRepo

    @Inject
    lateinit var database: EventDatabase

    override fun onReceive(context: Context?, intent: Intent?) {
        fun getString(@StringRes res: Int, vararg args: Any?) = context!!.getString(res, args)

        CoroutineScope(Dispatchers.IO).launch {
            val preEvents = database.dao().getEvents()
            val receiveNewEventNotification =
                Data.read(context!!, PathConfig.NewEventNotification, "false").toBoolean()
            eventRepo.reload().collect { result ->
                when (result) {
                    is EventRepoResult.Success -> {
                        val newEvents = result.events
                        eventRepo.save(
                            newEvents,
                            endAction = {}
                        )
                        if (receiveNewEventNotification) {
                            val diffEvents = newEvents.filterNot { preEvents.contains(it) }
                            val diffEventNames = diffEvents.map { it.name }
                            NotificationUtil.showInboxStyleNotification(
                                context = context,
                                id = Random.nextInt(),
                                channelId = getString(R.string.notification_channel_name),
                                title = getString(R.string.receiver_notification_title_new_event),
                                content = getString(
                                    R.string.receiver_notification_new_events_size,
                                    diffEventNames.size
                                ),
                                boxText = diffEventNames,
                                icon = R.drawable.ic_round_event_note_24,
                                isOnGoing = false
                            )
                        }
                    }
                    is EventRepoResult.Error -> {
                        val exception = result.exception.message!!
                        NotificationUtil.showNormalNotification(
                            context = context,
                            id = Random.nextInt(),
                            channelId = getString(R.string.notification_channel_name),
                            title = getString(R.string.receiver_notification_error_when_reload_events),
                            content = exception,
                            icon = R.drawable.ic_round_event_note_24,
                            isOnGoing = false
                        )
                    }
                }
            }
        }
    }
}
