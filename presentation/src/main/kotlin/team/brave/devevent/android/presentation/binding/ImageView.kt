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
import team.brave.devevent.android.presentation.R

@BindingAdapter("app:loadUrl")
fun imageViewLoadUrl(imageView: ImageView, url: String) {
    val context = imageView.context

    val thumbnailGlide = Glide
        .with(context)
        .load(url)
        .sizeMultiplier(0.25f)

    val errorGlide = Glide
        .with(context)
        .load(R.drawable.round_broken_image_24)
        .centerInside()

    Glide
        .with(context)
        .load(url)
        .thumbnail(thumbnailGlide)
        .error(errorGlide)
        .into(imageView)
}
