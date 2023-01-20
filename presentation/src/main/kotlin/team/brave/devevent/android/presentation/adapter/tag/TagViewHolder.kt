/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [TagViewHolder.kt] created by Ji Sungbin on 23. 1. 20. 오후 9:51
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.adapter.tag

import android.graphics.Color
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updateMarginsRelative
import androidx.recyclerview.widget.RecyclerView
import team.brave.devevent.android.domain.model.Tag
import team.brave.devevent.android.presentation.databinding.LayoutEventTagBinding
import team.brave.devevent.android.presentation.util.dp2px

class TagViewHolder(private val binding: LayoutEventTagBinding) : RecyclerView.ViewHolder(binding.root) {
    fun setTag(tag: Tag) {
        val isDarkColor = ColorUtils.calculateLuminance(Color.parseColor(tag.hexColor)) < 0.5
        binding.tag = tag
        if (isDarkColor) {
            val context = binding.root.context
            binding.chipTag.setTextColor(ContextCompat.getColor(context, android.R.color.white))
        }
    }

    fun enableSpacing() {
        binding.chipTag.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMarginsRelative(end = 8.dp2px)
        }
    }

    fun disableSpacing() {
        binding.chipTag.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            updateMarginsRelative(end = 0.dp2px)
        }
    }
}
