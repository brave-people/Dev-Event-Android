/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [LazyEvent.kt] created by Ji Sungbin on 21. 6. 22. 오후 9:34.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.main.event

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.event.database.EventEntity
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.ui.searcher.LazySearcher

private val eventVm = EventViewModel.instance

@Composable
fun Event(eventEntity: EventEntity) {
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
                    .clickable { favorite = !favorite }
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

@Composable
fun LazyEvent(eventFilter: EventFilter) {
    var search by remember { mutableStateOf("") }
    val eventEntities = eventVm.eventEntities.filter {
        when (eventFilter) {
            EventFilter.None -> if (search.isNotBlank()) it.contains(search) else true
            EventFilter.Favorite -> it.favorite
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazySearcher { search = value.text }
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(eventEntities) { event ->
                Event(event)
            }
        }
    }
}