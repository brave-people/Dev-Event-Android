/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [SearcherViewModel.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:45.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.searcher

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

class SearcherViewModel private constructor() : ViewModel() {

    private val _nowFocusedSearcher = mutableStateOf(0)
    private val textFields: HashMap<Int, MutableState<TextFieldValue>> = hashMapOf()
    private val _searchers = SnapshotStateList<Int>()

    val searchers: List<Int> get() = _searchers
    val nowFocusedSearcher: State<Int> get() = _nowFocusedSearcher

    fun updateFocusedSearcher(id: Int) {
        _nowFocusedSearcher.value = id
    }

    fun addSearcher(id: Int, value: String) {
        _searchers.add(id)
        textFields[id] = mutableStateOf(TextFieldValue(value))
    }

    fun removeSearcher(id: Int) {
        _searchers.remove(id)
    }

    fun getTextField(id: Int) = textFields[id]!!

    companion object {
        val instance by lazy { SearcherViewModel() }
    }
}