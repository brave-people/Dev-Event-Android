/*
 * DevEventAndroid © 2021 용감한 친구들. all rights reserved.
 * DevEventAndroid license is under the MIT.
 *
 * [ErrorDialog.kt] created by Ji Sungbin on 21. 7. 2. 오후 9:46.
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE.
 */

package team.bravepeople.devevent.ui.errordialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import team.bravepeople.devevent.R

@Composable
fun ErrorDialog(
    visible: MutableState<Boolean>,
    exception: Exception,
    onDismiss: () -> Unit = {}
) {
    if (visible.value) {
        AlertDialog(
            onDismissRequest = {
                visible.value = false
                onDismiss()
            },
            buttons = {},
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.error_dialog_occur_exception),
                        color = Color.Black
                    )
                    exception.message?.let { errorMessage ->
                        Text(text = errorMessage, color = Color.Black)
                    }
                }
            }
        )
    }
}
