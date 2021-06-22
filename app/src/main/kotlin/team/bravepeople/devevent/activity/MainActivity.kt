/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [MainActivity.kt] created by Ji Sungbin on 21. 6. 22. 오후 6:44.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

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
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import team.bravepeople.devevent.R
import team.bravepeople.devevent.theme.MaterialTheme
import team.bravepeople.devevent.theme.SystemUiController
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.searcher.LazySearcher
import team.bravepeople.devevent.ui.searcher.SearcherViewModel

class MainActivity : ComponentActivity() {

    private val searcherVm = SearcherViewModel.instance
    private var searching by mutableStateOf(false)
    private var searchField by mutableStateOf(TextFieldValue())

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
        TopAppBar {
            AnimatedVisibility(searching, exit = fadeOut()) {
                TextField(
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        searcherVm.addSearcher(Random.nextInt(), searchField.text)
                        searchField = TextFieldValue()
                        searching = false
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
                            imageVector = Icons.Rounded.Cancel,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                searchField = TextFieldValue("")
                            }
                        )
                    }
                )
            }
            AnimatedVisibility(!searching, enter = fadeIn()) {
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Settings,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                searching = true
                                focusManager.moveFocus(FocusDirection.In)
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Main() {
        println("START")
        Column(modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)) {
            LazySearcher {
                println("AAAAAAAAA")
                println(value.text)
            }
        }
    }
}