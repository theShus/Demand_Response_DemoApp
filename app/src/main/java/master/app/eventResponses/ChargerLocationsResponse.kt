package master.app.eventResponses

import ChargerLocation
import DREvent

data class ChargerLocationsResponse(
    val message: String,
    val data: List<ChargerLocation>
)
