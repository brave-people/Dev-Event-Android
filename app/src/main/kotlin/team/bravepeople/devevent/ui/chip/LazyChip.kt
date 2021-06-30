/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazyChip.kt] created by Ji Sungbin on 21. 6. 29. 오전 4:43.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import team.bravepeople.devevent.ui.flowrow.FlowRow
import team.bravepeople.devevent.util.ColorUtil

private val vm = ChipViewModel.instance

@Composable
private fun Chip(name: String, onClick: String.() -> Unit) {
    val shape = RoundedCornerShape(5.dp)
    val color = ColorUtil.getRandom()
    val isDarkColor = ColorUtils.calculateLuminance(color) < 0.5
    val selected = vm.isChipSelected(name).value

    Surface(
        shape = shape,
        color = if (selected) Color(color) else Color.White,
        elevation = 1.dp,
        border = BorderStroke(1.dp, Color(color)),
        modifier = Modifier
            .wrapContentSize()
            .clip(shape)
            .clickable { onClick(name) }
    ) {
        Text(
            text = name,
            color = if (selected) if (isDarkColor) Color.White else Color.Black else Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
        )
    }
}

@Composable
fun FlowTag(
    modifier: Modifier,
    tags: List<String>,
    onClick: String.() -> Unit
) { // used in setting dialog -> chip clickable
    FlowRow(modifier = modifier, verticalGap = 4.dp, horizontalGap = 4.dp) {
        for (tag in tags) {
            Chip(tag, onClick)
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
        items(tags) { tagName ->
            Chip(name = tagName, onClick = {})
        }
    }
}