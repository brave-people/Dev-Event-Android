/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [MainActivity.kt] created by Ji Sungbin on 22. 12. 7. 오후 9:30
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import team.brave.devevent.android.presentation.databinding.ActivityMainBinding
import team.brave.devevent.android.presentation.util.toast
import team.brave.devevent.android.presentation.viewmodel.BnvMenu
import team.brave.devevent.android.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        lifecycleScope.launch {
            launch {
                vm.getAllEvents()
            }

            repeatOnLifecycle(Lifecycle.State.CREATED) {
                vm.exception
                    .flowWithLifecycle(
                        lifecycle = lifecycle,
                        minActiveState = Lifecycle.State.CREATED,
                    )
                    .onEach { exception ->
                        toast(getString(R.string.activity_main_toast_exception_occurred, exception.message.orEmpty()))
                        if (BuildConfig.DEBUG) exception.printStackTrace()
                        if (!BuildConfig.DEBUG) Firebase.crashlytics.recordException(exception)
                    }
                    .collect()
            }
        }

        binding.bnvNavigator.setOnItemSelectedListener { item ->
            if (item.itemId == binding.bnvNavigator.selectedItemId) return@setOnItemSelectedListener false

            when (item.itemId) {
                R.id.menu_all_events -> {
                    true
                }
                R.id.menu_favorite_events -> {
                    true
                }
                R.id.menu_settings -> {
                    true
                }
                else -> false
            }
        }

        binding.bnvNavigator.setOnItemReselectedListener { item ->
            val menu = when (item.itemId) {
                R.id.menu_all_events -> BnvMenu.AllEvent
                R.id.menu_favorite_events -> BnvMenu.FavoriteEvent
                R.id.menu_settings -> BnvMenu.Setting
                else -> null
            }
            menu?.let(vm::menuReselected)
        }
    }
}
