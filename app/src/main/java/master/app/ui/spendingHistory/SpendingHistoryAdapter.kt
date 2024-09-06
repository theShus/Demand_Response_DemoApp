package master.app.ui.spendingHistory

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import master.app.R

class SpendingHistoryAdapter(private val spendingList: List<SpendingItem>) :
    RecyclerView.Adapter<SpendingHistoryAdapter.SpendingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.spending_history_item, parent, false)
        return SpendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        val item = spendingList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = spendingList.size

    inner class SpendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val monthTextView: TextView = itemView.findViewById(R.id.spendingMonth)
        private val amountTextView: TextView = itemView.findViewById(R.id.spendingAmount)
        private val totalCostTextView: TextView = itemView.findViewById(R.id.spendingTotalCost)

        fun bind(spendingItem: SpendingItem) {
            monthTextView.text = spendingItem.month
            amountTextView.text = spendingItem.amount
            totalCostTextView.text = "Total: ${spendingItem.totalCost} RSD"
            // Set color based on whether the value is positive (green) or negative (red)
            amountTextView.setTextColor(if (spendingItem.isPositive) Color.GREEN else Color.RED)
        }
    }
}
