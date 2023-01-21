/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [datastore.kt] created by Ji Sungbin on 23. 1. 22. 오전 4:22
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.datastore

import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object PreferenceKey {
    private const val PackageName = "team.brave.devevent.android.datastore"
    internal val Name = buildPreferenceKey(token = "root")

    @Suppress("NOTHING_TO_INLINE")
    private inline fun buildPreferenceKey(type: String? = null, token: String): String {
        return "$PackageName.${type?.plus(".").orEmpty()}$token"
    }

    object Event {
        val FavoriteId = stringSetPreferencesKey(buildPreferenceKey(type = "event", token = "favorite_id"))
    }
}

val Context.dataStore by preferencesDataStore(
    name = PreferenceKey.Name,
)
