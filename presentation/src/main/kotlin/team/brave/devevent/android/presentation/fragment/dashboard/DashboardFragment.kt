/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DashboardFragment.kt] created by Ji Sungbin on 23. 1. 18. 오후 11:27
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import team.brave.devevent.android.datastore.PreferenceKey
import team.brave.devevent.android.datastore.dataStore
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.domain.model.Tag
import team.brave.devevent.android.domain.model.toTimeString
import team.brave.devevent.android.presentation.adapter.event.EventAdapter
import team.brave.devevent.android.presentation.adapter.event.EventItemClickListener
import team.brave.devevent.android.presentation.databinding.FragmentEventsBinding
import team.brave.devevent.android.presentation.viewmodel.BnvMenu
import team.brave.devevent.android.presentation.viewmodel.MainViewModel

class DashboardFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels()
    private var events: List<Event> = emptyList()
    private var favoriteEvents: Map<Int, Boolean> = emptyMap()
    var isFavoriteScreen = false
        set(value) {
            field = value
            binding.isFavorite = value
            initAdapter()
        }

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentEventsBinding
            .inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    vm.events
                        .flowWithLifecycle(
                            lifecycle = viewLifecycleOwner.lifecycle,
                            minActiveState = Lifecycle.State.CREATED,
                        )
                        .filterNotNull()
                        .collect { events ->
                            @Suppress("ReplaceGetOrSet")
                            val favoriteEventIdSet = requireContext().applicationContext.dataStore.data.first()
                                .get(PreferenceKey.Event.FavoriteId).orEmpty()

                            this@DashboardFragment.events = events
                            favoriteEvents = buildMap {
                                favoriteEventIdSet.forEach { favoriteEventId ->
                                    set(favoriteEventId.toInt(), true)
                                }
                            }
                            initAdapter(updateModels = true)
                        }
                }

                launch {
                    vm.reselectMenu
                        .flowWithLifecycle(
                            lifecycle = viewLifecycleOwner.lifecycle,
                            minActiveState = Lifecycle.State.CREATED,
                        )
                        .filter { it == if (isFavoriteScreen) BnvMenu.FavoriteEvent else BnvMenu.AllEvent }
                        .collect {
                            if (binding.rvEvents.layoutManager?.isSmoothScrolling == false) {
                                binding.rvEvents.smoothScrollToPosition(0)
                            } else {
                                binding.rvEvents.scrollToPosition(0)
                            }
                        }
                }
            }
        }

        binding.isFavorite = isFavoriteScreen
        binding.rvEvents.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        _binding?.unbind()
        _binding = null
        super.onDestroyView()
    }

    fun saveScrollState() {
        val manager = binding.rvEvents.layoutManager as LinearLayoutManager
        vm.updateLastScrollPosition(
            isFavorite = isFavoriteScreen,
            position = manager.findFirstCompletelyVisibleItemPosition(),
        )
    }

    // TODO: RecyclerView 최적화
    private fun initAdapter(updateModels: Boolean = false) {
        val eventItemClickListener = object : EventItemClickListener {
            override fun onFavoriteClick(event: Event) {
                vm.toggleEventFavorite(event.id)
            }

            override fun onShareClick(event: Event) {
                val title = "${event.title} 이벤트 공유"
                val message = """
                    이 이벤트 어때요?
                    
                    [${event.title}]
                    주최: ${event.organizer}
                    ${event.toTimeString()}
                    태그: ${event.tags.map(Tag::name).joinToString(", ")}
                    
                    ${event.link}
                """.trimIndent()
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    putExtra(Intent.EXTRA_TEXT, message)
                }
                startActivity(Intent.createChooser(intent, title))
            }
        }

        val lastPosition = vm.getLastScrollPosition(isFavoriteScreen)
        if (binding.rvEvents.adapter == null || updateModels) {
            val adapter = EventAdapter(
                events = events,
                favoriteEventIds = favoriteEvents.toMutableMap(),
                eventItemClickListener = eventItemClickListener,
            )
            adapter.setHasStableIds(true)
            binding.rvEvents.adapter = adapter
        }
        (binding.rvEvents.adapter as EventAdapter).favoriteFilter = isFavoriteScreen
        binding.rvEvents.scrollToPosition(lastPosition)
    }
}
