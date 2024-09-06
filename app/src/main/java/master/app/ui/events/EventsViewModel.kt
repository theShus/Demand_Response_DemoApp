package master.app.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class DREvent(
    val title: String,
    val date: String,
    val description: String,
    val status: String,
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
    val county: String,
    var isRegistered: Boolean = false // Track whether the user has registered
)


class EventsViewModel : ViewModel() {

    private val _residentialEvents = MutableLiveData<List<DREvent>>().apply {
        value = listOf(
            DREvent(
                title = "Smart Energy Program",
                date = "2024-09-15",
                description = "Join this program to reduce your peak energy usage.",
                status = "Air Conditioners Are Being Cycled Off",
                startDate = "2024-09-15",
                endDate = "2024-09-15",
                startTime = "03:00 AM",
                endTime = "04:00 AM",
                county = "CLARK, FRESNO, IMPERIAL, ... (other counties)"
            ),
            DREvent(
                title = "Summer Discount Plan",
                date = "2024-09-20",
                description = "Air Conditioning Plan for energy savings.",
                status = "Cycling Event in Progress",
                startDate = "2024-09-20",
                endDate = "2024-09-20",
                startTime = "02:00 PM",
                endTime = "05:00 PM",
                county = "ORANGE, RIVERSIDE, SAN DIEGO, ... (other counties)"
            )
        )
    }

    private val _commercialEvents = MutableLiveData<List<DREvent>>().apply {
        value = listOf(
            DREvent(
                title = "Agricultural & Pumping Interruptible",
                date = "2024-09-25",
                description = "Reduce your energy use in agricultural operations.",
                status = "Load Shedding",
                startDate = "2024-09-25",
                endDate = "2024-09-25",
                startTime = "04:00 PM",
                endTime = "06:00 PM",
                county = "KERN, MONO, SAN DIEGO, ... (other counties)"
            ),
            DREvent(
                title = "Base Interruptible Program",
                date = "2024-09-28",
                description = "A program to reduce commercial energy usage during peak times.",
                status = "Reduction in Process",
                startDate = "2024-09-28",
                endDate = "2024-09-28",
                startTime = "01:00 PM",
                endTime = "03:00 PM",
                county = "LOS ANGELES, VENTURA, SAN BERNARDINO, ... (other counties)"
            )
        )
    }

    fun getResidentialEvents(): LiveData<List<DREvent>> {
        return _residentialEvents
    }

    fun getCommercialEvents(): LiveData<List<DREvent>> {
        return _commercialEvents
    }
}
