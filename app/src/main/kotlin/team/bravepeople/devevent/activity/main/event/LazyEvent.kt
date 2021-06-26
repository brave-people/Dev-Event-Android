/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazyEvent.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:34.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationSpec
import com.airbnb.lottie.compose.rememberLottieAnimationState
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.database.EventEntity
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.searcher.LazySearcher

private val eventVm = EventViewModel.instance
private var preListFirstVisibleIndex = 0

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
private fun Event(eventEntity: EventEntity) {
    val shape = RoundedCornerShape(15.dp)
    var favorite by remember { mutableStateOf(eventEntity.favorite) }

    Box(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clip(shape)
            .border(1.dp, colors.primary, shape)
            .padding(start = 15.dp, end = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = eventEntity.name,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 50.dp)
            )
            Text(
                text = eventEntity.category ?: "",
                fontSize = 13.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(if (favorite) R.drawable.ic_round_favorite_24 else R.drawable.ic_round_favorite_border_24),
                contentDescription = null,
                tint = Color(0xFFF9A825),
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        favorite = !favorite
                        eventEntity.favorite = favorite
                        eventVm.updateEvent(eventEntity)
                    }
            )
            Text(
                text = eventEntity.headerDate,
                fontSize = 13.sp,
                color = Color.LightGray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LazyEvent(eventFilter: EventFilter) {
    val listState = rememberLazyListState()
    var searcherVisible by remember { mutableStateOf(true) }
    var searching by remember { mutableStateOf(false) }

    var search by remember { mutableStateOf("") }
    var eventEntities = eventVm.eventEntities.filter {
        if (eventFilter == EventFilter.Favorite) it.favorite
        else true
    }
    eventEntities = eventEntities.filter {
        if (search.isNotBlank()) {
            searching = true
            it.contains(search)
        } else {
            searching = false
            true
        }
    }

    if (eventFilter == EventFilter.Favorite && eventEntities.isEmpty()) {
        EmptyEvent()
    } else {
        searcherVisible = preListFirstVisibleIndex < listState.firstVisibleItemIndex
        preListFirstVisibleIndex = listState.firstVisibleItemIndex

        Box(modifier = Modifier.fillMaxSize()) {
            AnimatedVisibility(
                visible = searcherVisible || searching,
                modifier = Modifier.zIndex(9999f)
            ) {
                LazySearcher { search = text }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                state = listState
            ) {
                items(eventEntities) { event ->
                    Event(event)
                }
            }
        }
    }
}