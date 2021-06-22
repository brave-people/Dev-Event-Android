/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazySearcher.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:44.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

private val vm = SearcherViewModel.instance

@Composable
fun LazySearcher(onSearcherChanged: MutableState<TextFieldValue>.() -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp)
    ) {
        items(vm.getSearcherIds()) { id ->
            Searcher(
                modifier = Modifier.clickable {
                    onSearcherChanged(vm.getTextField(id))
                },
                id = id
            )
        }
    }
}
