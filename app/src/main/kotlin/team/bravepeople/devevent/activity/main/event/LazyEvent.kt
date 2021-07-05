/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazyEvent.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:34.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event

import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.database.EventEntity
import team.bravepeople.devevent.activity.main.event.repo.EventRepo
import team.bravepeople.devevent.activity.main.event.repo.EventRepoResult
import team.bravepeople.devevent.theme.ColorOrange
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.bottomsheet.BottomSheet
import team.bravepeople.devevent.ui.chip.ChipViewModel
import team.bravepeople.devevent.ui.chip.LazyTag
import team.bravepeople.devevent.ui.errordialog.ErrorDialog
import team.bravepeople.devevent.util.Web
import team.bravepeople.devevent.util.extension.takeIfLength
import team.bravepeople.devevent.util.extension.takeIfSizeToCategory
import team.bravepeople.devevent.util.extension.toast

@Composable
private fun EmptyEvent() {
    val animationSpec = remember { LottieAnimationSpec.RawRes(R.raw.empty) }
    val animationState =
        rememberLottieAnimationState(repeatCount = Integer.MAX_VALUE)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            spec = animationSpec,
            animationState = animationState,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = stringResource(R.string.event_empty_favorite),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
private fun EventBottomSheet(
    event: EventEntity?,
    sheetVisible: MutableState<Boolean>
) {
    val context = LocalContext.current
    val unknown = "정보없음"

    fun String?.toTags() = if (isNullOrBlank()) listOf() else split(",")

    if (event != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(PaddingValues(16.dp)),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_round_cancel_24),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        sheetVisible.value = false
                    }
            )
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
                Button(onClick = {
                    if (event.site != null) {
                        Web.open(context, event.site)
                    } else {
                        toast(context, context.getString(R.string.event_toast_unknown_site))
                    }
                }) {
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
private fun EventItem(eventVm: EventViewModel, event: EventEntity, onClick: () -> Unit) {
    val shape = RoundedCornerShape(15.dp)
    var favorite by remember { mutableStateOf(event.favorite) }

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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(if (favorite) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24),
                contentDescription = null,
                tint = ColorOrange,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        favorite = !favorite
                        event.favorite = favorite
                        eventVm.update(event)
                    }
            )
            Text(
                text = (event.joinDate ?: event.startDate)?.takeIfLength(18) ?: event.headerDate,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class,
    InternalCoroutinesApi::class
)
@Composable
fun LazyEvent(
    eventRepo: EventRepo,
    eventVm: EventViewModel,
    chipVm: ChipViewModel,
    search: String,
    eventFilter: EventFilter
) {
    val coroutineScope = rememberCoroutineScope()

    var exception by remember { mutableStateOf(Exception()) }
    val errorDialogVisible = remember { mutableStateOf(false) }

    var selectedEvent by remember { mutableStateOf<EventEntity?>(null) }
    val bottomSheetVisible = remember { mutableStateOf(selectedEvent != null) }

    val listState = rememberLazyListState()
    var refreshing by remember { mutableStateOf(false) }

    var eventEntities = eventVm.eventEntityFlow.collectAsState().value.filter {
        if (eventFilter == EventFilter.Favorite) it.favorite
        else true
    }
    eventEntities = eventEntities.filter { it.contains(search) }

    if (chipVm.selectedChip.isNotEmpty()) {
        eventEntities = eventEntities.filter { event ->
            if (event.category == null) false
            else chipVm.selectedChip.any { event.category.contains(it) }
        }
    }

    if (eventFilter == EventFilter.Favorite && eventEntities.isEmpty()) {
        EmptyEvent()
    } else {
        BottomSheet(
            bottomSheetContent = {
                EventBottomSheet(
                    event = selectedEvent,
                    sheetVisible = bottomSheetVisible
                )
            },
            contentHeight = 300.dp,
            bottomSheetVisible = bottomSheetVisible,
            content = {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(refreshing),
                    indicator = { state, refreshTrigger ->
                        SwipeRefreshIndicator(
                            state = state,
                            refreshTriggerDistance = refreshTrigger,
                            contentColor = colors.primary
                        )
                    },
                    onRefresh = {
                        coroutineScope.launch(Dispatchers.IO) {
                            refreshing = true
                            eventRepo.reload().collect { result ->
                                when (result) {
                                    is EventRepoResult.Success -> {
                                        eventRepo.save(
                                            result.events,
                                            endAction = {
                                                delay(1000)
                                                refreshing = false
                                                eventVm.reload()
                                            }
                                        )
                                    }
                                    is EventRepoResult.Error -> {
                                        exception = result.exception
                                        errorDialogVisible.value = true
                                        refreshing = false
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    ErrorDialog(visible = errorDialogVisible, exception = exception)

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        state = listState,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) { // todo: events change animation; https://stackoverflow.com/a/64922966/14299073
                        val eventGroup = eventEntities.groupBy { it.headerDate }
                        eventGroup.forEach { (headerDate, events) ->
                            stickyHeader {
                                EventHeader(headerDate)
                            }

                            items(events.sortedBy { it.startDate ?: it.joinDate }) { event ->
                                EventItem(
                                    eventVm = eventVm,
                                    event = event,
                                    onClick = {
                                        selectedEvent = event
                                        bottomSheetVisible.value = true
                                    }
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}