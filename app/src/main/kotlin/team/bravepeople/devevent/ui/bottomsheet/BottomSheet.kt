/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [BottomSheet.kt] created by Ji Sungbin on 21. 6. 29. 오전 3:27.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import team.bravepeople.devevent.util.extension.noRippleClickable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BottomSheet(
    content: @Composable () -> Unit,
    bottomSheetContent: @Composable ColumnScope.() -> Unit,
    contentHeight: Dp,
    bottomSheetVisible: MutableState<Boolean>
) {
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart) {
        AnimatedVisibility(
            visible = bottomSheetVisible.value,
            enter = expandVertically(),
            exit = shrinkVertically(),
            modifier = Modifier.zIndex(9999f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(contentHeight)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consumeAllChanges()
                            if (dragAmount.y > 0) { // down
                                bottomSheetVisible.value = false
                            }
                        }
                    }
                    .clip(shape)
                    .background(Color.White, shape)
                    .shadow(1.dp, shape)
                    .noRippleClickable { }
            ) {
                bottomSheetContent()
            }
        }
        content()
    }
}
