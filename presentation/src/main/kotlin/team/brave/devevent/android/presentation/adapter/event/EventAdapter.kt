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

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventViewHolder>() {
    private val eventSize = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutEventBinding.inflate(inflater, parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.setEvent(events[position])
        holder.setTags(events[position].tags)
        if (position != eventSize - 1) {
            holder.enableSpacing()
        } else {
            holder.disableSpacing()
        }
    }

    override fun getItemCount() = eventSize

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = EventViewType
}