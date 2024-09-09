import master.app.RegisterRequest
import master.app.RegisterResponse
import master.app.UserResponse
import master.app.eventResponses.ChargerLocationsResponse
import master.app.eventResponses.EventsResponse
import master.app.eventResponses.PriceDataResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // GET request to fetch data from DynamoDB (via Lambda)
    @GET("events")
    fun getEvents(): Call<EventsResponse> // Replace with your data model

    // GET request to fetch data from DynamoDB (via Lambda)
    @GET("chargerLocations")
    fun getChargerLocations(): Call<ChargerLocationsResponse> // Replace with your data model

    @GET("prices")
    fun getPriceData(): Call<PriceDataResponse> // Replace with your data model

    @POST("users")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @GET("users")
    fun loginUser(@Query("email") email: String): Call<UserResponse>

}
