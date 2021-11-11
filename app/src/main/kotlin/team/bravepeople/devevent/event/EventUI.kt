/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [EventUI.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:34.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.event

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.bravepeople.devevent.R
import team.bravepeople.devevent.event.domain.Event
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.chip.ChipViewModel
import team.bravepeople.devevent.ui.chip.LazyTag
import team.bravepeople.devevent.util.Web
import team.bravepeople.devevent.util.extension.takeIfLength
import team.bravepeople.devevent.util.extension.takeIfSizeToCategory
import team.bravepeople.devevent.util.extension.toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EventBottomSheet(event: Event?) {
    if (event == null) return

    val context = LocalContext.current
    val unknown = "정보없음"

    fun String?.toTags() = if (isNullOrBlank()) listOf() else split(",")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(PaddingValues(20.dp)),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = event.name,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 40.dp)
            )
            Text(
                text = with(event) {
                    val nowTime by lazy {
                        SimpleDateFormat("MM.dd(E) kk:mm", Locale.KOREA).format(Date())
                            .replace("24:", "00:")
                    }

                    """
                        주최: ${owner ?: unknown}
                        신청날짜: ${joinDate?.replace("~", " ~ ") ?: unknown}
                        시작날짜: ${startDate?.replace("~", " ~ ") ?: unknown}
                        현재시각: $nowTime
                    """.trimIndent()
                },
                lineHeight = 20.sp,
                color = Color.Gray,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 15.dp)
            )
            LazyTag(
                modifier = Modifier.padding(top = 15.dp),
                tags = event.category.toTags()
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (event.site != null) {
                        Web.open(context, event.site)
                    } else {
                        toast(context, context.getString(R.string.event_toast_unknown_site))
                    }
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_round_home_24),
                    contentDescription = null
                )
                Text(
                    text = "사이트 방문",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun EventHeader(headerDate: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(color = colors.secondary)
            .padding(start = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = headerDate, color = Color.White, fontSize = 15.sp)
    }
}

@Composable
private fun EventItem(event: Event, onClick: () -> Unit) {
    val shape = RoundedCornerShape(15.dp)

    Box(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape)
            .border(1.dp, colors.primary, shape)
            .clickable { onClick() }
            .padding(start = 15.dp, end = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = event.name,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 50.dp)
            )
            Text(
                text = event.category?.split(",")?.takeIfSizeToCategory(3) ?: "",
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = (event.joinDate ?: event.startDate)?.takeIfLength(18) ?: event.headerDate,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyEvent(
    chipVm: ChipViewModel,
    search: State<TextFieldValue>,
    onEventClickAction: (Event) -> Unit
) {
    val selectedChips by chipVm.selectedChips.collectAsState()
    var events = EventStore.events.filter { it.contains(search.value.text) }

    if (selectedChips.isNotEmpty()) {
        events = events.filter { event ->
            if (event.category == null) false
            else selectedChips.any { chip -> event.category.contains(chip) }
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val eventGroup = events.groupBy { it.headerDate }
        eventGroup.forEach { (headerDate, events) ->
            stickyHeader {
                EventHeader(headerDate)
            }

            items(
                items = events.sortedBy { it.startDate ?: it.joinDate },
                key = { event -> event.id }
            ) { event ->
                EventItem(
                    event = event,
                    onClick = { onEventClickAction(event) }
                )
            }
        }
    }
}
