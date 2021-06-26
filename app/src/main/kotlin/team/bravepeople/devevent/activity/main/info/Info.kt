/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Info.kt] created by Ji Sungbin on 21. 6. 24. 오전 12:35.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.info

import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import team.bravepeople.devevent.R
import team.bravepeople.devevent.theme.ColorOrange
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.glideimage.GlideImage
import team.bravepeople.devevent.util.Web
import team.bravepeople.devevent.util.extension.doDelay

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Info(activity: Activity) {
    val context = LocalContext.current

    var lottieVisible by remember { mutableStateOf(true) }
    val projectUrl = "https://github.com/brave-people/Dev-Event-Android"
    val organizationUrl = "https://github.com/brave-people"
    val logoUrl = "https://avatars.githubusercontent.com/u/68955947?s=200&v=4"

    val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.confetti) }
    val animationState =
        rememberLottieAnimationState(autoPlay = true).apply { speed = .8f }

    doDelay(2500) { lottieVisible = false }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = lottieVisible,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(9999f),
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            LottieAnimation(
                spec = animationSpec,
                animationState = animationState,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable { Web.open(activity, organizationUrl) }
                        .clip(RoundedCornerShape(10.dp)),
                    src = logoUrl
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.Black,
                        fontSize = 25.sp,
                        modifier = Modifier.clickable { Web.open(activity, projectUrl) }
                    )
                    Text(
                        text = stringResource(R.string.info_description_app),
                        color = Color.Gray,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = ColorOrange)
                ) {
                    Text(text = "앱 전체 데이터 초기화", color = Color.White)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.secondary),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "용감한 친구들")
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.copyright),
                color = Color.Black,
                fontSize = 10.sp
            )
        }
    }
}