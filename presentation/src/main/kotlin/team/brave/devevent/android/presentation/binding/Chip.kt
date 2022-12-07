/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [Chip.kt] created by Ji Sungbin on 22. 12. 8. 오전 3:35
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.binding

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip

@BindingAdapter("app:backgroundColor")
fun setChipBackgroundColor(chip: Chip, hexColor: String) {
    chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(hexColor))
}
