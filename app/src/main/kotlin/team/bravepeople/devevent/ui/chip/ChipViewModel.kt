/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [ChipViewModel.kt] created by Ji Sungbin on 21. 6. 30. 오후 8:08.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.chip

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChipViewModel : ViewModel() {

    private val _selectedChip = MutableStateFlow(emptyList<String>())
    val selectedChip = _selectedChip.asStateFlow()

    fun toggleChipSelected(name: String) {
        if (_selectedChip.value.contains(name)) {
            _selectedChip.update { oldValue -> oldValue.toMutableList().apply { remove(name) } }
        } else {
            _selectedChip.update { oldValue -> oldValue.toMutableList().apply { add(name) } }
        }
    }

    fun reset() {
        _selectedChip.update { emptyList() }
    }
}
