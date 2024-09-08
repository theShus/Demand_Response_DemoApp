import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import master.app.eventResponses.EventsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    var isRegistered: Boolean = false,
    var isResidential: Boolean = false
)

class EventsViewModel : ViewModel() {

    // Use MutableLiveData to store the list of events
    private val _events = MutableLiveData<List<DREvent>?>()
    val events: MutableLiveData<List<DREvent>?> get() = _events // Expose the events list as LiveData

    init {
        fetchData()
    }

    // Fetch data from the API
    fun fetchData() {
        println("API Call has been initiated")
        val call = ApiClient.apiService.getEvents()

        call.enqueue(object : Callback<EventsResponse> {
            override fun onResponse(call: Call<EventsResponse>, response: Response<EventsResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        // Update the LiveData when new data is fetched
                        _events.value = data.data
                    }
                } else {
                    println("GET request failed")
                    println(response)
                }
            }

            override fun onFailure(call: Call<EventsResponse>, t: Throwable) {
                println("GET request failed: $t")
            }
        })
    }
}
