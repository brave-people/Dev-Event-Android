/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [ErrorDialog.kt] created by Ji Sungbin on 21. 7. 2. 오후 9:46.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.errordialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import team.bravepeople.devevent.R

@Composable
fun ErrorDialog(visible: MutableState<Boolean>, exception: Exception) {
    if (visible.value) {
        val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.error) }
        val animationState =
            rememberLottieAnimationState(repeatCount = Int.MAX_VALUE).apply {
                speed = 2f
            }

        AlertDialog(
            onDismissRequest = { visible.value = false },
            buttons = {},
            title = { // todo: Centering
                LottieAnimation(
                    spec = animationSpec,
                    animationState = animationState,
                    modifier = Modifier.size(100.dp)
                )
            },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "에러가 발생했어요 \uD83D\uDE22", color = Color.Black)
                    exception.message?.let { errorMessage ->
                        Text(text = errorMessage, color = Color.Black)
                    }
                }
            }
        )
    }
}