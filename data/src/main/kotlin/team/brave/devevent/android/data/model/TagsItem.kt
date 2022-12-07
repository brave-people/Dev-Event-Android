/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [TagsItem.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class TagsItem(
    @field:JsonProperty("tag_name")
    val tagName: String? = null,

    @field:JsonProperty("tag_color")
    val tagColor: String? = null,

    @field:JsonProperty("id")
    val id: Int? = null,
)
