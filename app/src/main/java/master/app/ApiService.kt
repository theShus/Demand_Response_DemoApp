import master.app.eventResponses.ChargerLocationsResponse
import master.app.eventResponses.EventsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // GET request to fetch data from DynamoDB (via Lambda)
    @GET("events")
    fun getEvents(): Call<EventsResponse> // Replace with your data model

    // GET request to fetch data from DynamoDB (via Lambda)
    @GET("chargerLocations")
    fun getChargerLocations(): Call<ChargerLocationsResponse> // Replace with your data model

}
