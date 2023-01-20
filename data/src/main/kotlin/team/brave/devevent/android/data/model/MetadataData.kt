/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [Metadata.kt] created by Ji Sungbin on 23. 1. 20. 오후 11:09
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.data.model

import com.squareup.moshi.Json

internal data class MetadataData(
    @Json(name = "total")
    val total: Int? = null,

    @Json(name = "month")
    val month: Int? = null,

    @Json(name = "year")
    val year: Int? = null,
)
