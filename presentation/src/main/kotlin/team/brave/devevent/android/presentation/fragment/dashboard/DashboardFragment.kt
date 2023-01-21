/*
 * DevEvent-Android © 2022 Ji Sungbin. all rights reserved.
 * DevEvent-Android license is under the MIT.
 *
 * [DashboardFragment.kt] created by Ji Sungbin on 23. 1. 18. 오후 11:27
 *
 * Please see: https://github.com/brave-people/Dev-Event-Android/blob/master/LICENSE
 */

package team.brave.devevent.android.presentation.fragment.dashboard

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import team.brave.devevent.android.datastore.PreferenceKey
import team.brave.devevent.android.datastore.dataStore
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.presentation.adapter.event.EventAdapter
import team.brave.devevent.android.presentation.adapter.event.EventItemClickListener
import team.brave.devevent.android.presentation.databinding.FragmentEventsBinding
import team.brave.devevent.android.presentation.viewmodel.BnvMenu
import team.brave.devevent.android.presentation.viewmodel.MainViewModel


class DashboardFragment : Fragment() {
    private val args: DashboardFragmentArgs by navArgs()
    private val vm: MainViewModel by activityViewModels()

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

                            updateEvents(
                                events = events,
                                favoriteEventIdMap = buildMap {
                                    favoriteEventIdSet.forEach { favoriteEventId ->
                                        set(favoriteEventId.toInt(), true)
                                    }
                                },
                            )
                        }
                }

                launch {
                    vm.reselectMenu
                        .flowWithLifecycle(
                            lifecycle = viewLifecycleOwner.lifecycle,
                            minActiveState = Lifecycle.State.CREATED,
                        )
                        .filter { it == if (args.isFavorite) BnvMenu.FavoriteEvent else BnvMenu.AllEvent }
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

        binding.isFavorite = args.isFavorite
        binding.rvEvents.setHasFixedSize(true)
    }

    override fun onPause() {
        super.onPause()
        val manager = binding.rvEvents.layoutManager as LinearLayoutManager
        vm.updateLastScrollPosition(
            position = manager.findFirstCompletelyVisibleItemPosition(),
            isFavorite = args.isFavorite,
        )
    }

    override fun onDestroyView() {
        _binding?.unbind()
        _binding = null
        super.onDestroyView()
    }

    // TODO: RecyclerView 최적화
    private fun updateEvents(events: List<Event>, favoriteEventIdMap: Map<Int, Boolean>) {
        val filteredEvents = if (args.isFavorite) {
            events.filter { event -> favoriteEventIdMap[event.id] ?: false }
        } else {
            events
        }
        val eventItemClickListener = object : EventItemClickListener {
            override fun onFavoriteClick(event: Event) {
                vm.toggleEventFavorite(event.id)
            }

            override fun onShareClick(event: Event) {
                vm.shareEvent(event)
            }
        }

        val isFavorite = args.isFavorite
        val lastPosition = vm.getLastScrollPosition(isFavorite)
        val adapter = EventAdapter(
            events = filteredEvents,
            favoriteEventIds = favoriteEventIdMap.toMutableMap(),
            eventItemClickListener = eventItemClickListener,
        )

        binding.rvEvents.adapter = adapter
        binding.rvEvents.scrollToPosition(lastPosition)
    }
}
