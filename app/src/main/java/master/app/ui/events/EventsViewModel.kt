package master.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class DREvent(val title: String, val date: String, val description: String)

class EventsViewModel : ViewModel() {

    private val _events = MutableLiveData<List<DREvent>>().apply {
        value = listOf(
            DREvent("Peak Load Reduction", "2024-09-15", "Reduce consumption during peak hours."),
            DREvent("Energy Efficiency Campaign", "2024-09-20", "Participate in lowering demand from 6 PM to 8 PM."),
            DREvent("Sustainable Energy Event", "2024-09-25", "Help stabilize the grid by reducing energy usage.")
        )
    }

    fun getEvents(): LiveData<List<DREvent>> {
    val events: LiveData<List<DREvent>> = _events
        return events
    }
}
