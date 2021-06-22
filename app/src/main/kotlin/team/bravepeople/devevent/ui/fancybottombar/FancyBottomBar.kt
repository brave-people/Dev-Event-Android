/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [FancyBottomBar.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:38.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.fancybottombar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

private interface FancyItemListener {
    fun onItemChanged(item: FancyItem)
}

private var listener: FancyItemListener? = null

@Composable
fun FancyBottomBar(
    fancyColors: FancyColors = FancyColors(),
    fancyOptions: FancyOptions = FancyOptions(),
    items: List<FancyItem>,
    action: FancyItem.() -> Unit
) {
    listener = object : FancyItemListener {
        override fun onItemChanged(item: FancyItem) {
            action(item)
        }
    }

    var fancyItemState by remember { mutableStateOf(items.first().id) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(fancyOptions.barHeight)
            .background(color = fancyColors.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val fancyItemColor by animateColorAsState(if (fancyItemState == item.id) fancyColors.primary else fancyColors.indicatorBackground)

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        fancyItemState = item.id
                        listener?.onItemChanged(item)
                    }
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(fancyOptions.indicatorHeight)
                        .background(color = fancyItemColor)
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (item.icon != null) {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = fancyItemColor)
                        )
                    }
                    if (item.title.isNotBlank()) {
                        Text(
                            text = item.title,
                            color = fancyItemColor,
                            modifier = Modifier.padding(top = fancyOptions.titleTopPadding),
                            fontFamily = fancyOptions.fontFamily
                        )
                    }
                }
            }
        }
    }
}
