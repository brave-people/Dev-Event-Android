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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun FancyBottomBar(
    modifier: Modifier,
    fancyColors: FancyColors = FancyColors(),
    fancyOptions: FancyOptions = FancyOptions(),
    items: List<FancyItem>,
    onItemChanged: FancyItem.() -> Unit
) {
    var fancyItemState by remember { mutableStateOf(items.first().id) }

    Row(
        modifier = modifier
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
                        onItemChanged(item)
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
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            tint = fancyItemColor
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
