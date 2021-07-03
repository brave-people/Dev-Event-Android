/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [MainActivity.kt] created by Ji Sungbin on 21. 6. 22. 오후 3:41.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.EventFilter
import team.bravepeople.devevent.activity.main.event.EventViewModel
import team.bravepeople.devevent.activity.main.event.LazyEvent
import team.bravepeople.devevent.activity.main.event.database.EventDatabase
import team.bravepeople.devevent.activity.main.event.repo.EventRepo
import team.bravepeople.devevent.activity.main.info.Info
import team.bravepeople.devevent.service.ForegroundService
import team.bravepeople.devevent.theme.MaterialTheme
import team.bravepeople.devevent.theme.SystemUiController
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.theme.defaultFontFamily
import team.bravepeople.devevent.ui.chip.ChipViewModel
import team.bravepeople.devevent.ui.chip.FlowTag
import team.bravepeople.devevent.ui.fancybottombar.FancyBottomBar
import team.bravepeople.devevent.ui.fancybottombar.FancyColors
import team.bravepeople.devevent.ui.fancybottombar.FancyItem
import team.bravepeople.devevent.ui.fancybottombar.FancyOptions
import team.bravepeople.devevent.util.AlarmUtil
import team.bravepeople.devevent.util.Data
import team.bravepeople.devevent.util.config.PathConfig
import team.bravepeople.devevent.util.extension.isServiceRunning
import team.bravepeople.devevent.util.extension.toast

private enum class Tab {
    Main, Favorite, Info
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var tab by mutableStateOf(Tab.Main)
    private val chipVm: ChipViewModel by viewModels()
    private val eventVm: EventViewModel by viewModels()

    @Inject
    lateinit var eventRepo: EventRepo

    @Inject
    lateinit var eventDatabase: EventDatabase

    private var searching by mutableStateOf(false)
    private var searchField by mutableStateOf(TextFieldValue())

    private val bottomItems = listOf(
        FancyItem(icon = R.drawable.ic_round_event_note_24, id = 0),
        FancyItem(icon = R.drawable.ic_round_favorite_24, id = 1),
        FancyItem(icon = R.drawable.ic_round_info_24, id = 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val autoEventReload = Data.read(
            applicationContext,
            PathConfig.AutoEventReload,
            "false"
        ).toBoolean()

        if (autoEventReload && !isServiceRunning(ForegroundService::class.java)) {
            startService(Intent(this, ForegroundService::class.java))
            AlarmUtil.addReloadTask()
        }

        SystemUiController(window).run {
            setStatusBarColor(colors.primary)
            setNavigationBarColor(Color.White)
        }

        setContent {
            MaterialTheme {
                Scaffold(topBar = { TopBar() }, content = { Main() })
            }
        }
    }

    @Composable
    fun EventOptionDialog(openDialogVisible: MutableState<Boolean>, tags: List<String>) {
        if (openDialogVisible.value) {
            AlertDialog(
                onDismissRequest = { openDialogVisible.value = false },
                confirmButton = {
                    OutlinedButton(onClick = { openDialogVisible.value = false }) {
                        Text(text = stringResource(R.string.close))
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { chipVm.reset() }) {
                        Text(text = stringResource(R.string.main_dialog_reset_category))
                    }
                },
                title = { Text(text = stringResource(R.string.main_dialog_choose_category)) },
                text = {
                    FlowTag(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        chipVm = chipVm,
                        tags = tags,
                        onClick = {
                            chipVm.toggleChipSelected(this)
                        }
                    )
                }
            )
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun TopBar() {
        val context = LocalContext.current

        val eventOptionDialogVisible = remember { mutableStateOf(false) }
        var tags by remember { mutableStateOf(eventVm.getAllTags()) }

        EventOptionDialog(openDialogVisible = eventOptionDialogVisible, tags = tags)

        TopAppBar {
            AnimatedVisibility(visible = searching, exit = fadeOut()) {
                TextField(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        val search = searchField.text
                        if (search.isNotBlank()) {
                            searchField = TextFieldValue()
                            searching = false
                        } else {
                            toast(context, getString(R.string.main_toast_input_search))
                        }
                    }),
                    modifier = Modifier.fillMaxSize(),
                    singleLine = true,
                    value = searchField,
                    onValueChange = { searchField = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White,
                        textColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_round_cancel_24),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                searchField = TextFieldValue()
                                searching = false
                            }
                        )
                    }
                )
            }
            AnimatedVisibility(visible = !searching, enter = fadeIn()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.app_name),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                    if (tab != Tab.Info) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Settings,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(end = 8.dp)
                                    .clickable {
                                        tags = eventVm.getAllTags()
                                        eventOptionDialogVisible.value = true
                                    },
                                tint = Color.White
                            )
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.clickable {
                                    searching = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Main() {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (events, bottomBar) = createRefs()

            Column(modifier = Modifier
                .constrainAs(events) {
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomBar.top)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
            ) {
                Crossfade(tab) { target ->
                    when (target) {
                        Tab.Info -> Info(
                            database = eventDatabase,
                            activity = this@MainActivity
                        )
                        else -> LazyEvent(
                            eventRepo = eventRepo,
                            eventVm = eventVm,
                            chipVm = chipVm,
                            search = searchField.text,
                            eventFilter = if (target == Tab.Main) EventFilter.None else EventFilter.Favorite
                        )
                    }
                }
            }
            FancyBottomBar(
                modifier = Modifier.constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom)
                },
                items = bottomItems,
                fancyColors = FancyColors(primary = colors.primary),
                fancyOptions = FancyOptions(fontFamily = defaultFontFamily)
            ) {
                tab = when (id) {
                    0 -> Tab.Main
                    1 -> Tab.Favorite
                    2 -> Tab.Info
                    else -> throw Error("Unknown FancyItem type.")
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        AlarmUtil.destroy()
        eventRepo.save(
            eventEntities = eventVm.eventEntityFlow.value,
            endAction = {}
        )
    }
}
