/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [ImageView.kt] created by Ji Sungbin on 22. 12. 8. 오전 2:04
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadUrl")
fun imageViewLoadUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}
