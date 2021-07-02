/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [ChipViewModel.kt] created by Ji Sungbin on 21. 6. 30. 오후 8:08.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.chip

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ChipViewModel : ViewModel() {

    private val _selectedChip = mutableStateListOf<String>()
    val selectedChip: List<String> get() = _selectedChip

    fun toggleChipSelected(name: String) {
        if (_selectedChip.contains(name)) {
            _selectedChip.remove(name)
        } else {
            _selectedChip.add(name)
        }
    }

    fun isChipSelected(name: String): State<Boolean> {
        return mutableStateOf(_selectedChip.contains(name))
    }
}