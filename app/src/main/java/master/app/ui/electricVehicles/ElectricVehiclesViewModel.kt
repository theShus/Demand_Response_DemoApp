import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import master.app.eventResponses.ChargerLocationsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class ChargerLocation(
    val title: String,
    val latitude: Double,
    val longitude: Double
)

class ElectricVehiclesViewModel : ViewModel() {

    // LiveData that holds the charger locations
    private val _chargerLocations = MutableLiveData<List<ChargerLocation>>()
    val chargerLocations: LiveData<List<ChargerLocation>> get() = _chargerLocations

    // Fetch charger locations from an API (for demonstration purposes)
    fun fetchChargerLocations() {
        val call = ApiClient.apiService.getChargerLocations() // Make sure this matches your API call

        call.enqueue(object : Callback<ChargerLocationsResponse> {
            override fun onResponse(call: Call<ChargerLocationsResponse>, response: Response<ChargerLocationsResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _chargerLocations.value = data.data
                    } // Update LiveData with fetched locations
                } else {
                    println("Failed to fetch charger locations: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ChargerLocationsResponse>, t: Throwable) {
                println("Failed to fetch charger locations: $t")
            }
        })
    }
}
