/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventAdapter.kt] created by Ji Sungbin on 23. 1. 20. 오후 9:50
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.adapter.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.presentation.databinding.LayoutEventBinding

private const val EventViewType = 0

class EventAdapter(
    private var events: List<Event>,
    // 매번 contains 하는 시간 없애기 위해 Map 으로 변환
    private val favoriteEventIds: MutableMap<Int, Boolean>,
    private val eventItemClickListener: EventItemClickListener,
) : RecyclerView.Adapter<EventViewHolder>() {
    private val originalEvents = events
    private var eventSize = events.size
    var favoriteFilter = false
        set(value) {
            field = value
            val filteredEvents = if (value) {
                originalEvents.filter { event -> favoriteEventIds[event.id] ?: false }
            } else {
                originalEvents
            }
            events = filteredEvents
            eventSize = filteredEvents.size

            @Suppress("NotifyDataSetChanged")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutEventBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.setEvent(event = event, isFavorite = favoriteEventIds[event.id] ?: false)
        holder.setTags(event.tags)
        holder.setEventClickListener(
            listener = eventItemClickListener,
            toggleFavoriteEventIdMap = {
                favoriteEventIds[event.id] = !(favoriteEventIds[event.id] ?: false)
            },
        )

        if (position != eventSize - 1) {
            holder.enableSpacing()
        } else {
            holder.disableSpacing()
        }
    }

    override fun getItemCount() = eventSize

    override fun getItemId(position: Int) = events[position].id.toLong()

    override fun getItemViewType(position: Int) = EventViewType
}
