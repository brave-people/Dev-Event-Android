/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [Searcher.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:44.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import team.bravepeople.devevent.theme.colors

private val vm = SearcherViewModel.instance

@Composable
fun Searcher(modifier: Modifier, id: Int) {
    val shape = RoundedCornerShape(15.dp)
    val field = vm.getTextField(id)

    Surface(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .wrapContentWidth()
            .height(50.dp),
        border = BorderStroke(1.dp, colors.primary),
        shape = shape
    ) {
        TextField(
            singleLine = true,
            value = field.value,
            onValueChange = { field.value = it },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.Black,
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}
