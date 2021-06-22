/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Searcher.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:44.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.searcher

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import team.bravepeople.devevent.theme.colors

private val vm = SearcherViewModel.instance

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun Searcher(id: Int, onSearcherChanged: State<TextFieldValue>.() -> Unit) {
    val focusManager = LocalFocusManager.current
    var enable by remember { mutableStateOf(false) }

    val shape = RoundedCornerShape(10.dp)
    val field = vm.getTextField(id)

    Surface(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .height(50.dp)
            .width(100.dp),
        border = BorderStroke(1.dp, colors.primary),
        shape = shape
    ) {
        TextField(
            enabled = enable,
            singleLine = true,
            value = field.value,
            onValueChange = { field.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                enable = false
            }),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .wrapContentWidth()
                .combinedClickable(
                    onClick = {
                        println("3")
                        onSearcherChanged(field)
                    },
                    onLongClick = {
                        println("4")
                        enable = true
                    }
                )
                .focusRequester(FocusRequester())
                .onFocusChanged {
                    if (it.isFocused) {
                        onSearcherChanged(field)
                    }
                }
        )
    }
}
