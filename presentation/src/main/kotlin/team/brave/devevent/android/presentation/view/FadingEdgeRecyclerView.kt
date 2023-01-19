/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [FadingEdgeRecyclerView.kt] created by Ji Sungbin on 23. 1. 19. 오후 8:43
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

// https://stackoverflow.com/a/56338638/14299073
class FadingEdgeRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {
    override fun isPaddingOffsetRequired() = true
    override fun getTopPaddingOffset() = -paddingTop
    override fun getBottomPaddingOffset() = paddingBottom
}
