package master.app.eventResponses

import DREvent
import master.app.ui.currentPrices.PriceData

data class PriceDataResponse(
    val message: String,
    val data: List<PriceData>
)
