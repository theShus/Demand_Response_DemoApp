package master.app.ui.currentPrices

import ApiClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarEntry
import master.app.eventResponses.PriceDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Data class to represent price information
data class PriceData(
    val id: Int,
    val value: Float,
    val additionalInfo: String,
)

class CurrentPricesViewModel : ViewModel() {

    // MutableLiveData for chart entries
    private val _chartEntries = MutableLiveData<List<BarEntry>>()
    val chartEntries: LiveData<List<BarEntry>> get() = _chartEntries

    // LiveData for each zone's additional info
    private val _greenZoneInfo = MutableLiveData<String>()
    val greenZoneInfo: LiveData<String> get() = _greenZoneInfo

    private val _blueZoneInfo = MutableLiveData<String>()
    val blueZoneInfo: LiveData<String> get() = _blueZoneInfo

    private val _redZoneInfo = MutableLiveData<String>()
    val redZoneInfo: LiveData<String> get() = _redZoneInfo

    // Initialize ViewModel
    init {
        fetchChartDataFromAPI()
    }

    private fun fetchChartDataFromAPI() {
        // Use the ApiClient to make a GET request to the backend
        val call = ApiClient.apiService.getPriceData()

        call.enqueue(object : Callback<PriceDataResponse> {
            override fun onResponse(call: Call<PriceDataResponse>, response: Response<PriceDataResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { priceResponse ->
                        // Sort the data by 'id'
                        val sortedData = priceResponse.data.sortedBy { it.id }

                        // Convert the sorted API data to chart entries
                        val barEntries = sortedData.mapIndexed { index, priceData ->
                            BarEntry(index.toFloat(), priceData.value)
                        }

                        // Update LiveData with the sorted chart entries
                        _chartEntries.value = barEntries

                        // Set the additionalInfo for each zone based on the sorted data
                        if (sortedData.size >= 3) {
                            _greenZoneInfo.value = sortedData[0].additionalInfo
                            _blueZoneInfo.value = sortedData[1].additionalInfo
                            _redZoneInfo.value = sortedData[2].additionalInfo
                        }
                    }
                } else {
                    // Handle error case, set fallback text
                    _greenZoneInfo.value = "Failed to fetch data"
                    _blueZoneInfo.value = "Failed to fetch data"
                    _redZoneInfo.value = "Failed to fetch data"
                }
            }

            override fun onFailure(call: Call<PriceDataResponse>, t: Throwable) {
                // Handle network failure case
                _greenZoneInfo.value = "Failed to connect to the server"
                _blueZoneInfo.value = "Failed to connect to the server"
                _redZoneInfo.value = "Failed to connect to the server"
            }
        })
    }
}

