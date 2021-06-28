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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.theme.ColorOrange
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.glideimage.GlideImage
import team.bravepeople.devevent.ui.license.License
import team.bravepeople.devevent.ui.license.Licenser
import team.bravepeople.devevent.ui.license.Project
import team.bravepeople.devevent.util.Web
import team.bravepeople.devevent.util.extension.doDelay
import team.bravepeople.devevent.util.extension.toast

@Composable
private fun OpenSourceDialog(isOpen: MutableState<Boolean>) {
    if (isOpen.value) {
        AlertDialog(
            onDismissRequest = { isOpen.value = false },
            buttons = {},
            title = {
                Text(
                    text = stringResource(R.string.info_opensource_license),
                    fontSize = 20.sp
                )
            },
            text = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Licenser(
                        listOf(
                            Project(
                                "Kotlin",
                                "https://github.com/JetBrains/kotlin",
                                License.Apache2
                            ),
                            Project("Gradle", "https://github.com/gradle/gradle", License.Apache2),
                            Project(
                                "Android Icons",
                                "https://www.apache.org/licenses/LICENSE-2.0.txt",
                                License.Apache2
                            ),
                            Project(
                                "kotlinx.coroutines",
                                "https://github.com/Kotlin/kotlinx.coroutines",
                                License.Apache2
                            ),
                            Project(
                                "CoreKtx",
                                "https://android.googlesource.com/platform/frameworks/support/",
                                License.Apache2
                            ),
                            Project(
                                "lottie",
                                "https://github.com/airbnb/lottie/blob/master/android-compose.md",
                                License.MIT
                            ),
                            Project("glide", "https://github.com/bumptech/glide", License.BSD),
                            Project(
                                "Browser",
                                "https://developer.android.com/jetpack/androidx/releases/browser",
                                License.Apache2
                            ),
                            Project(
                                "CrashReporter",
                                "https://github.com/MindorksOpenSource/CrashReporter",
                                License.Apache2
                            ),
                            Project("okhttp", "https://github.com/square/okhttp", License.Apache2),
                            Project(
                                "retrofit",
                                "https://github.com/square/retrofit",
                                License.Apache2
                            ),
                            Project(
                                "Room",
                                "https://developer.android.com/jetpack/androidx/releases/room",
                                License.Apache2
                            ),
                            Project("Hilt", "https://dagger.dev/hilt/", License.Apache2),
                            Project(
                                "Jetpack Compose",
                                "https://developer.android.com/jetpack/compose",
                                License.Apache2
                            ),
                            Project(
                                "leakcanary",
                                "https://github.com/square/leakcanary",
                                License.Apache2
                            ),
                            Project(
                                "바른나눔고딕",
                                "https://help.naver.com/support/contents/contents.help?serviceNo=1074&categoryNo=3497",
                                License.CUSTOM("SIL")
                            ),
                        )
                    )
                }
            })
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Info(activity: Activity) {
    val context = LocalContext.current

    var lottieVisible by remember { mutableStateOf(true) }
    val projectUrl = "https://github.com/brave-people/Dev-Event-Android"
    val organizationUrl = "https://github.com/brave-people"
    val logoUrl = "https://avatars.githubusercontent.com/u/68955947?s=200&v=4"

    var dbClearButtonLastClick = 0L
    val isOpensourceDialogOpen = remember { mutableStateOf(false) }

    val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.confetti) }
    val animationState =
        rememberLottieAnimationState(autoPlay = true).apply { speed = .8f }

    OpenSourceDialog(isOpensourceDialogOpen)
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
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        val clickedTime = System.currentTimeMillis()
                        if (clickedTime - dbClearButtonLastClick > 2000) {
                            toast(
                                context,
                                activity.getString(R.string.info_button_confirm_clear_db)
                            )
                            dbClearButtonLastClick = clickedTime
                        } else {
                            Thread {
                                EventDatabase.instance.clearAllTables()
                            }.start()
                            toast(context, activity.getString(R.string.info_button_cleared_db))
                            activity.finish()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = ColorOrange)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_round_warning_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(R.string.info_button_clear_all_db),
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colors.secondary)
                ) {
                    Text(
                        text = stringResource(R.string.info_button_app_information),
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { isOpensourceDialogOpen.value = true }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_round_source_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = stringResource(R.string.info_opensource_license),
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
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