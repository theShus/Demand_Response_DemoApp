data class SpendingItem(
    val month: String,
    val amount: String,
    val isPositive: Boolean // true for savings (green), false for extra cost (red)
)
