/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [SplashActivity.kt] created by Ji Sungbin on 21. 6. 21. 오전 12:41.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.bravepeople.devevent.R
import team.bravepeople.devevent.theme.MaterialTheme
import team.bravepeople.devevent.theme.SystemUiController
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.util.extension.doDelay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        doDelay(2000) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        SystemUiController(window).setSystemBarsColor(colors.primary)
        setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colors.primary)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.app_name),
                            color = Color.White,
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = stringResource(R.string.copyright),
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
