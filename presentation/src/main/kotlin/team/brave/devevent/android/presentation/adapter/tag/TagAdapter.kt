/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventAdapter.kt] created by Ji Sungbin on 23. 1. 20. 오후 9:52
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.adapter.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import team.brave.devevent.android.domain.model.Tag
import team.brave.devevent.android.presentation.databinding.LayoutEventTagBinding

private const val TagViewType = 0

class TagAdapter(private val tags: List<Tag>) : RecyclerView.Adapter<TagViewHolder>() {
    private val tagSize = tags.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutEventTagBinding.inflate(inflater, parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.setTag(tags[position])
        if (position != tagSize - 1) {
            holder.enableSpacing()
        } else {
            holder.disableSpacing()
        }
    }

    override fun getItemCount() = tagSize

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = TagViewType
}
