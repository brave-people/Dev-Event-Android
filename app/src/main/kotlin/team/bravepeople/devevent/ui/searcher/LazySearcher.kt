/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazySearcher.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:44.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.searcher

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import team.bravepeople.devevent.R
import team.bravepeople.devevent.theme.colors

private val vm = SearcherViewModel.instance

@Composable
fun LazySearcher(onSearcherChanged: TextFieldValue.() -> Unit) {
    val shape = RoundedCornerShape(10.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, start = 16.dp)
    ) {
        if (vm.searchers.isNotEmpty()) {
            Icon(
                painter = painterResource(R.drawable.ic_round_cancel_24),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .zIndex(9999f)
                    .border(1.dp, colors.primary, shape)
                    .background(Color.White, shape)
                    .clickable {
                        vm.clear()
                        onSearcherChanged(TextFieldValue(text = ""))
                    }
                    .size(50.dp)
                    .padding(10.dp)
            )
        }
        LazyRow(
            modifier = Modifier
                .padding(start = 25.dp)
                .fillMaxWidth()
                .height(50.dp),
            contentPadding = PaddingValues(start = (25 + 8).dp, end = 8.dp)
        ) {
            items(vm.searchers) { id -> // todo: Item add/remove animation
                Searcher(id, onSearcherChanged)
            }
        }
    }
}
