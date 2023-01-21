/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventViewHolder.kt] created by Ji Sungbin on 23. 1. 20. 오후 9:50
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.adapter.event

import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.recyclerview.widget.RecyclerView
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.model.Tag
import team.brave.devevent.android.presentation.adapter.tag.TagAdapter
import team.brave.devevent.android.presentation.databinding.LayoutEventBinding
import team.brave.devevent.android.presentation.util.dp2px

class EventViewHolder(private val binding: LayoutEventBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setEvent(event: Event, isFavorite: Boolean) {
        binding.event = event
        binding.isFavorite = isFavorite
    }

    private fun toggleFavorite() {
        binding.isFavorite = !(binding.isFavorite ?: error("isFavorite not initialized"))
    }

    fun setEventClickListener(
        listener: EventItemClickListener,
        toggleFavoriteEventIdMap: () -> Unit,
    ) {
        binding.ivFavorite.setOnClickListener {
            listener.onFavoriteClick(binding.event ?: error("event not initialized"))
            toggleFavorite()
            toggleFavoriteEventIdMap()
        }
        binding.ivShare.setOnClickListener {
            listener.onShareClick(binding.event ?: error("event not initialized"))
        }
    }

    fun enableSpacing() {
        binding.cvRoot.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMargins(bottom = 16.dp2px)
        }
    }

    fun disableSpacing() {
        binding.cvRoot.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMargins(bottom = 0.dp2px)
        }
    }

    fun setTags(tags: List<Tag>) {
        val adapter = TagAdapter(tags)
        binding.rvTag.setHasFixedSize(true)
        binding.rvTag.adapter = adapter
    }
}
