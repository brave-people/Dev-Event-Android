/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [Metadata.kt] created by Ji Sungbin on 22. 12. 8. 오전 1:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.fasterxml.jackson.annotation.JsonProperty

internal data class Metadata(
    @field:JsonProperty("total")
    val total: Int? = null,

    @field:JsonProperty("month")
    val month: Int? = null,

    @field:JsonProperty("year")
    val year: Int? = null,
)
