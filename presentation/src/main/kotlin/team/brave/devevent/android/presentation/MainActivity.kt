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
import androidx.fragment.app.commit
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
import team.brave.devevent.android.presentation.fragment.dashboard.DashboardArgument
import team.brave.devevent.android.presentation.fragment.dashboard.DashboardFragment
import team.brave.devevent.android.presentation.fragment.settings.SettingFragment
import team.brave.devevent.android.presentation.util.toast
import team.brave.devevent.android.presentation.viewmodel.BnvMenu
import team.brave.devevent.android.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: MainViewModel by viewModels()

    private val dashboardFragment by lazy { DashboardFragment() }
    private val settingFragment by lazy { SettingFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        supportFragmentManager.commit {
            replace(
                R.id.fcv_navigator,
                dashboardFragment.apply { arguments = DashboardArgument(isFavorite = false).toBundle() }
            )
        }

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
                        if (BuildConfig.DEBUG) {
                            exception.printStackTrace()
                        } else {
                            Firebase.crashlytics.recordException(exception)
                        }
                    }
                    .collect()
            }
        }

        binding.bnvNavigator.setOnItemSelectedListener { item ->
            if (item.itemId == binding.bnvNavigator.selectedItemId) return@setOnItemSelectedListener false

            val navigateFragment = when (item.itemId) {
                R.id.menu_all_events -> dashboardFragment.apply {
                    arguments = DashboardArgument(isFavorite = false).toBundle()
                }
                R.id.menu_favorite_events -> dashboardFragment.apply {
                    arguments = DashboardArgument(isFavorite = true).toBundle()
                }
                R.id.menu_settings -> settingFragment
                else -> null
            }

            navigateFragment?.let { fragment ->
                supportFragmentManager.commit {
                    replace(R.id.fcv_navigator, fragment)
                }
                true
            } ?: false
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
