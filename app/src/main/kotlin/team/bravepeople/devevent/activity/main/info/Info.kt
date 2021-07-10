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
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.bravepeople.devevent.BuildConfig
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.theme.ColorOrange
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.glideimage.GlideImage
import team.bravepeople.devevent.ui.licenser.License
import team.bravepeople.devevent.ui.licenser.Licenser
import team.bravepeople.devevent.ui.licenser.Project
import team.bravepeople.devevent.util.AlarmUtil
import team.bravepeople.devevent.util.Battery
import team.bravepeople.devevent.util.Data
import team.bravepeople.devevent.util.Web
import team.bravepeople.devevent.util.config.PathConfig
import team.bravepeople.devevent.util.extension.noRippleClickable
import team.bravepeople.devevent.util.extension.noRippleLongClickable
import team.bravepeople.devevent.util.extension.toast


@Composable
fun ApplicationInfoDialog(isOpen: MutableState<Boolean>) {
    if (isOpen.value) {
        val context = LocalContext.current

        AlertDialog(
            modifier = Modifier.width(300.dp),
            onDismissRequest = { isOpen.value = false },
            buttons = {},
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = stringResource(R.string.info_dialog_notice_issue_and_bug),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Text(
                        text = stringResource(R.string.info_dialog_you_can_do_at_issue),
                        modifier = Modifier
                            .clickable { Web.open(context, Web.Link.Issue) }
                            .padding(top = 2.dp),
                        fontSize = 13.sp
                    )
                    Text(
                        text = stringResource(R.string.info_dialog_betatester),
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    Text(
                        text = stringResource(R.string.info_dialog_thanks_for_contribute_bug),
                        modifier = Modifier.padding(top = 2.dp),
                        fontSize = 13.sp
                    )
                    Text(
                        text = "앱 버전: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp
                    )
                    Text(
                        text = stringResource(R.string.info_dialog_thanks_covenant),
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = colors.primary,
                        fontSize = 10.sp
                    )
                }
            }
        )
    }
}

@Composable
private fun OpenSourceDialog(isOpen: MutableState<Boolean>) {
    if (isOpen.value) {
        AlertDialog(
            modifier = Modifier.width(300.dp),
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
                            Project(
                                "ConstraintLayout",
                                "https://developer.android.com/jetpack/compose/layouts/constraintlayout",
                                License.Apache2
                            )
                        )
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun Info(database: EventDatabase, activity: Activity) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    var dbClearButtonLastClick = 0L
    val isOpensourceDialogOpen = remember { mutableStateOf(false) }
    val isApplicationInfoDialogOpen = remember { mutableStateOf(false) }
    val logoUrl = "https://avatars.githubusercontent.com/u/68955947?s=200&v=4"

    var eventsKeyword by remember {
        mutableStateOf(
            TextFieldValue(
                Data.read(
                    context,
                    PathConfig.EventKeywordAlarm,
                    ""
                )!!
            )
        )
    }
    var newEventsNotification by remember {
        mutableStateOf(
            Data.read(
                context,
                PathConfig.NewEventNotification,
                "false"
            ).toBoolean()
        )
    }
    var autoEventReload by remember {
        mutableStateOf(
            Data.read(
                context,
                PathConfig.AutoEventReload,
                "false"
            ).toBoolean()
        )
    }

    OpenSourceDialog(isOpensourceDialogOpen)
    ApplicationInfoDialog(isApplicationInfoDialogOpen)

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (main, copyright) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(main) {
                    top.linkTo(parent.top)
                    bottom.linkTo(copyright.top, 8.dp)
                    height = Dimension.fillToConstraints
                }
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
                        .noRippleClickable { Web.open(activity, Web.Link.Organization) }
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
                        modifier = Modifier.noRippleClickable {
                            Web.open(
                                activity,
                                Web.Link.Project
                            )
                        }
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
                                activity.getString(R.string.info_button_confirm_clear_db),
                                Toast.LENGTH_LONG
                            )
                            dbClearButtonLastClick = clickedTime
                        } else {
                            coroutineScope.launch(Dispatchers.IO) {
                                database.clearAllTables()
                            }
                            Data.clear(context)
                            AlarmUtil.stopReloadService(context)
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
                    onClick = { isApplicationInfoDialogOpen.value = true },
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
            Text(
                text = stringResource(R.string.info_notice_events_reload_time),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                fontSize = 13.sp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.info_event_auto_reload))
                Switch(
                    checked = autoEventReload,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colors.primary,
                        checkedTrackColor = colors.secondary
                    ),
                    onCheckedChange = { checked ->
                        if (checked) {
                            toast(context, context.getString(R.string.info_toast_battery_life))
                            Battery.requestIgnoreOptimization(context)
                            AlarmUtil.startReloadService(context = context)
                        } else {
                            AlarmUtil.stopReloadService(context)
                        }
                        autoEventReload = checked
                        Data.save(context, PathConfig.AutoEventReload, checked.toString())
                    }
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.info_notification_new_event))
                Switch(
                    checked = newEventsNotification,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colors.primary,
                        checkedTrackColor = colors.secondary
                    ),
                    onCheckedChange = { checked ->
                        if (checked && !autoEventReload) {
                            toast(
                                context,
                                context.getString(R.string.info_toast_must_on_event_auto_reload)
                            )
                        }
                        newEventsNotification = checked
                        Data.save(context, PathConfig.NewEventNotification, checked.toString())
                    }
                )
            }
            Text(
                text = stringResource(R.string.info_notice_keyword_split_char),
                modifier = Modifier
                    .padding(top = 30.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                fontSize = 13.sp,
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.info_event_keyword_alarm))
                TextField(
                    value = eventsKeyword,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                    }),
                    onValueChange = {
                        eventsKeyword = it
                        Data.save(context, PathConfig.EventKeywordAlarm, it.text)
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .focusRequester(FocusRequester()),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(copyright) {
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.copyright),
                color = Color.Black,
                fontSize = 10.sp,
                modifier = Modifier.noRippleLongClickable {
                    toast(context, "\uD83D\uDE1B")
                }
            )
        }
    }
}