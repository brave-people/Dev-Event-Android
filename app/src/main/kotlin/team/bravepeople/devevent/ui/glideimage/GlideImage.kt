/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [GlideImage.kt] created by Ji Sungbin on 21. 6. 22. 오후 4:39.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.glideimage

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import team.bravepeople.devevent.GlideApp

@Composable
fun GlideImage(modifier: Modifier, src: Any) {
    val context = LocalContext.current

    AndroidView(factory = { ImageView(context) }, modifier = modifier) {
        GlideApp.with(context).load(src).into(it)
    }
}
