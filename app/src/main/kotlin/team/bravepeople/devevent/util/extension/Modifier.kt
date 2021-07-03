/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Modifier.kt] created by Ji Sungbin on 21. 7. 3. 오후 11:45.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.util.extension

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

// https://stackoverflow.com/a/66839858/14299073
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit) = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@OptIn(ExperimentalFoundationApi::class)
inline fun Modifier.noRippleLongClickable(crossinline onLongClick: () -> Unit) = composed {
    combinedClickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = {},
        onLongClick = { onLongClick() }
    )
}