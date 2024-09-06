package master.app.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import master.app.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EventsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        // Setup RecyclerView for Residential DR events
        binding.residentialEventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getResidentialEvents().observe(viewLifecycleOwner) { events ->
            binding.residentialEventsRecyclerView.adapter = EventsAdapter(events)
        }

        // Setup RecyclerView for Commercial DR events
        binding.commercialEventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getCommercialEvents().observe(viewLifecycleOwner) { events ->
            binding.commercialEventsRecyclerView.adapter = EventsAdapter(events)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
