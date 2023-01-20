/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [TagsItem.kt] created by Ji Sungbin on 23. 1. 20. 오후 11:09
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.squareup.moshi.Json

internal data class TagData(
    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "tag_name")
    val name: String? = null,

    @Json(name = "tag_color")
    val hexColor: String? = null,
)
