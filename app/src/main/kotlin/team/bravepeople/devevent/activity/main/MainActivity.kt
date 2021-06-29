/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [MainActivity.kt] created by Ji Sungbin on 21. 6. 22. 오후 3:41.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import dagger.hilt.android.AndroidEntryPoint
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.EventFilter
import team.bravepeople.devevent.activity.main.event.LazyEvent
import team.bravepeople.devevent.activity.main.info.Info
import team.bravepeople.devevent.repo.RepositoryViewModel
import team.bravepeople.devevent.theme.MaterialTheme
import team.bravepeople.devevent.theme.SystemUiController
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.theme.defaultFontFamily
import team.bravepeople.devevent.ui.fancybottombar.FancyBottomBar
import team.bravepeople.devevent.ui.fancybottombar.FancyColors
import team.bravepeople.devevent.ui.fancybottombar.FancyItem
import team.bravepeople.devevent.ui.fancybottombar.FancyOptions
import team.bravepeople.devevent.util.extension.toast

private enum class Tab {
    Event, Favorite, Info
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var tab by mutableStateOf(Tab.Event)
    private val repositoryVm: RepositoryViewModel by viewModels()

    private var searching by mutableStateOf(false)
    private var searchField by mutableStateOf(TextFieldValue())

    private val bottomItems = listOf(
        FancyItem(icon = R.drawable.ic_round_event_note_24, id = 0),
        FancyItem(icon = R.drawable.ic_round_favorite_24, id = 1),
        FancyItem(icon = R.drawable.ic_round_info_24, id = 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemUiController(window).setStatusBarColor(colors.primary)
        setContent {
            MaterialTheme {
                Scaffold(topBar = { TopBar() }, content = { Main() })
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun TopBar() {
        val context = LocalContext.current

        TopAppBar {
            AnimatedVisibility(visible = searching, exit = fadeOut()) {
                TextField(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        val search = searchField.text
                        if (search.isNotBlank()) {
                            searchField =
                                TextFieldValue(text = "") // todo: clear TextFieldValue not working
                            searching = false
                        } else {
                            toast(context, "검색어를 입력해 주세요.")
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
                                modifier = Modifier.padding(end = 8.dp),
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
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(bottom = 60.dp)) {
                Crossfade(tab) { target ->
                    when (target) {
                        Tab.Event -> LazyEvent(repositoryVm, searchField.text, EventFilter.None)
                        Tab.Favorite -> LazyEvent(
                            repositoryVm,
                            searchField.text,
                            EventFilter.Favorite
                        )
                        Tab.Info -> Info(this@MainActivity)
                    }
                }
            }
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
                FancyBottomBar(
                    items = bottomItems,
                    fancyColors = FancyColors(primary = colors.primary),
                    fancyOptions = FancyOptions(fontFamily = defaultFontFamily)
                ) {
                    tab = when (id) {
                        0 -> Tab.Event
                        1 -> Tab.Favorite
                        2 -> Tab.Info
                        else -> throw Error("Unknown FancyItem type.")
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        repositoryVm.save(applicationContext)
    }
}
