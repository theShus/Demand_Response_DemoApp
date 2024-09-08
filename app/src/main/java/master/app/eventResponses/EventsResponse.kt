package master.app.eventResponses

import DREvent

data class EventsResponse(
    val message: String,
    val data: List<DREvent>
)
