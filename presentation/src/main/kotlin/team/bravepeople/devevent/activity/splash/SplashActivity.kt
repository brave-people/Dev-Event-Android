/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [SplashActivity.kt] created by Ji Sungbin on 21. 6. 21. 오전 12:41.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import team.bravepeople.devevent.R
import team.bravepeople.devevent.activity.main.MainActivity
import team.bravepeople.devevent.activity.splash.mvi.MviSplashState
import team.bravepeople.devevent.event.EventStore
import team.bravepeople.devevent.theme.MaterialTheme
import team.bravepeople.devevent.theme.colors
import team.bravepeople.devevent.util.extension.doDelay
import team.bravepeople.devevent.util.extension.errorToast

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val vm: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.loadEvents()
        vm.observe(lifecycleOwner = this, state = ::handleState, sideEffect = null)
        setContent {
            MaterialTheme {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = colors.primary)
                        .padding(16.dp)
                ) {
                    val (title, copyright) = createRefs()

                    Text(
                        text = stringResource(R.string.app_name),
                        color = Color.White,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(title) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                    Text(
                        text = stringResource(R.string.copyright),
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.constrainAs(copyright) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                    )
                }
            }
        }
    }

    private fun handleState(state: MviSplashState) {
        if (!state.isException()) {
            if (state.loaded) {
                lifecycleScope.launchWhenCreated {
                    val events = state.events
                    if (events.isNotEmpty()) {
                        EventStore.update(events)
                        doDelay(1000) {
                            finish()
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        }
                    } else {
                        errorToast(Exception("이벤트 목록이 비었어요."))
                    }
                }
            }
        } else {
            errorToast(state.exception!!)
        }
    }

    override fun onBackPressed() {}
}
