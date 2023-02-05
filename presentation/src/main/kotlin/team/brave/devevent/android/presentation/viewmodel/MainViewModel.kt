/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [MainViewModel.kt] created by Ji Sungbin on 23. 1. 2. 오후 7:26
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

@file:Suppress("ConstPropertyName")

package team.brave.devevent.android.presentation.viewmodel

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import team.brave.devevent.android.datastore.PreferenceKey
import team.brave.devevent.android.datastore.dataStore
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.usecase.GetAllEventsUseCase
import team.brave.devevent.android.presentation.util.SaveableMutableStateFlow
import team.brave.devevent.android.presentation.util.context
import team.brave.devevent.android.presentation.util.mutate

private const val EventListSaveKey = "EventList"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase,
    application: Application,
    savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application) {
    private val _events = SaveableMutableStateFlow<List<Event>?>(
        savedStateHandle = savedStateHandle,
        key = EventListSaveKey,
        initialValue = null,
    )
    val events: StateFlow<List<Event>?> = _events.asStateFlow()

    private val _reselectMenu = Channel<BnvMenu>()
    val reselectMenu = _reselectMenu.receiveAsFlow()

    private val _exception = Channel<Throwable>()
    val exception = _exception.receiveAsFlow()

    suspend fun getAllEvents() {
        viewModelScope.launch {
            getAllEventsUseCase()
                .onSuccess { events ->
                    _events.value = events
                }
                .onFailure { exception ->
                    _exception.send(exception)
                }
        }
    }

    fun toggleEventFavorite(eventId: Int) {
        viewModelScope.launch {
            context.dataStore.edit { preference ->
                val favoriteEventIds = preference[PreferenceKey.Event.FavoriteId].orEmpty()
                val newFavoriteEventIds = favoriteEventIds.mutate {
                    if (!remove(eventId.toString())) add(eventId.toString())
                }
                preference[PreferenceKey.Event.FavoriteId] = newFavoriteEventIds
            }
        }
    }

    fun menuReselected(menu: BnvMenu) {
        viewModelScope.launch {
            _reselectMenu.send(menu)
        }
    }
}
