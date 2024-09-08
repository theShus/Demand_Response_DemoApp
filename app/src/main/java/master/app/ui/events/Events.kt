package master.app.ui.events

import EventsViewModel
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


        viewModel.fetchData()

        // Observe the LiveData from the ViewModel
        viewModel.events.observe(viewLifecycleOwner) { events ->
            // Setup RecyclerView for Residential DR events
            val residentialEvents = events?.filter { it.isResidential }
            binding.residentialEventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.residentialEventsRecyclerView.adapter = residentialEvents?.let {
                EventsAdapter(
                    it
                )
            }

            // Setup RecyclerView for Commercial DR events
            val nonResidentialEvents = events?.filter { !it.isResidential }
            binding.commercialEventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.commercialEventsRecyclerView.adapter = nonResidentialEvents?.let {
                EventsAdapter(
                    it
                )
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
