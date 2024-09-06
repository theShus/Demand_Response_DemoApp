package master.app.ui.spendingHistory

data class SpendingItem(
    val month: String,
    val amount: String,
    val totalCost: Float,  // New field for total electricity cost
    val isPositive: Boolean // true for savings (green), false for extra cost (red)
)
