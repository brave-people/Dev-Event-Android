/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazyChip.kt] created by Ji Sungbin on 21. 6. 29. 오전 4:43.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import team.bravepeople.devevent.ui.flowrow.FlowRow
import team.bravepeople.devevent.util.TagColor
import team.bravepeople.devevent.util.extension.noRippleClickable

@Composable
private fun Chip(
    name: String,
    selected: State<Boolean>,
    onClick: (String.() -> Unit)?
) {
    val context = LocalContext.current
    val shape = RoundedCornerShape(5.dp)
    val color = TagColor(context, name)
    val isDarkColor = ColorUtils.calculateLuminance(color) < 0.5
    var modifier = Modifier
        .wrapContentSize()
        .clip(shape)

    val textColor by animateColorAsState(if (selected.value) if (isDarkColor) Color.White else Color.Black else Color.Black)
    val surfaceColor by animateColorAsState(if (selected.value) Color(color) else Color.White)

    onClick?.let { action ->
        modifier = modifier.noRippleClickable { action(name) }
    }

    Surface(
        shape = shape,
        color = surfaceColor,
        elevation = 1.dp,
        border = BorderStroke(1.dp, animateColorAsState(Color(color)).value),
        modifier = modifier
    ) {
        Text(
            text = name,
            color = textColor,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
fun FlowTag(
    modifier: Modifier,
    chipVm: ChipViewModel,
    tags: List<String>,
    onClick: String.() -> Unit
) { // used in setting dialog -> chip clickable
    FlowRow(modifier = modifier, verticalGap = 4.dp, horizontalGap = 4.dp) {
        for (tag in tags) {
            Chip(
                name = tag,
                selected = chipVm.isChipSelected(tag),
                onClick = onClick
            )
        }
    }
}

@Composable
fun LazyTag(
    modifier: Modifier,
    tags: List<String>
) { // used in event detail bottomsheet -> chip unclickable
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags) { tag ->
            Chip(
                name = tag,
                selected = mutableStateOf(true),
                onClick = null
            )
        }
    }
}
