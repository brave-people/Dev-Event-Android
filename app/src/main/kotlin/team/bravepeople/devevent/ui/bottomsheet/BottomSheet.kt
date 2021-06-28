/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [BottomSheet.kt] created by Ji Sungbin on 21. 6. 29. 오전 3:27.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.bottomsheet

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun BottomSheet(
    content: @Composable () -> Unit,
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
    contentHeight: Dp,
    bottomSheetVisible: State<Boolean>
) {
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    val sheetContentHeight = if (bottomSheetVisible.value) contentHeight else 0.dp

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .height(sheetContentHeight)
                .clip(shape)
                .zIndex(999f)
                .background(Color.White, shape)
                .shadow(1.dp, shape)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { }
        ) {
            bottomSheetContent()
        }
        content()
    }
}