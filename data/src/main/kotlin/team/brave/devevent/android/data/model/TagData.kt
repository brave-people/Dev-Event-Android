/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [Tag.kt] created by Ji Sungbin on 23. 1. 1. 오후 3:08
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

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

internal data class TagData(
    @field:JsonProperty("id")
    val id: Int? = null,

    @field:JsonProperty("tag_name")
    val name: String? = null,

    @field:JsonProperty("tag_color")
    val hexColor: String? = null,
)
