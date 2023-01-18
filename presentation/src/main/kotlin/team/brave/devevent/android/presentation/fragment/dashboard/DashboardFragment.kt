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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.filterNotNull
import team.brave.devevent.android.domain.model.Event
import team.brave.devevent.android.presentation.R
import team.brave.devevent.android.presentation.adapter.EventAdapter
import team.brave.devevent.android.presentation.databinding.FragmentEventsBinding
import team.brave.devevent.android.presentation.viewmodel.MainViewModel

class DashboardFragment : Fragment() {
    private val vm: MainViewModel by activityViewModels()
    private val args: DashboardFragmentArgs by navArgs()

    private lateinit var binding: FragmentEventsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<FragmentEventsBinding>(inflater, R.layout.fragment_events, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isFavorite = args.isFavorite
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            vm.events
                .flowWithLifecycle(
                    lifecycle = viewLifecycleOwner.lifecycle,
                    minActiveState = Lifecycle.State.CREATED,
                )
                .filterNotNull()
                .collect { events ->
                    updateEvents(events)
                }
        }
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }

    // TODO: RecyclerView 최적화
    private fun updateEvents(events: List<Event>) {
        // TODO: 즐겨찾기 기능 연결
        val filteredEvents = if (args.isFavorite) emptyList() else events
        val adapter = EventAdapter(filteredEvents)
        binding.rvEvents.adapter = adapter
    }
}
