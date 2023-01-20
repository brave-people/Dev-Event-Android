/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [EventResponseItemItem.kt] created by Ji Sungbin on 23. 1. 20. 오후 11:09
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

// [NOTE] 매 달마다 행사가 EventResponseItem 으로 들어옴
// ex) 1월달 행사, 2월달 행사, 3월달 행사가 개별적으로 EventResponseItem 으로 들어옴
//     (month 단위)

internal data class EventResponseItem(
    @Json(name = "metadata")
    val metadata: MetadataData? = null,

    @Json(name = "dev_event")
    val events: List<EventData?>? = null,
) {
    companion object {
        @Suppress("HasPlatformType")
        @Json(ignore = true)
        val MoshiAdapter = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
            .adapter(Array<EventResponseItem>::class.java)
    }
}
