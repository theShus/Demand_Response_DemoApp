package master.app.ui.spendingHistory

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import master.app.databinding.FragmentSpendingHistoryBinding

class SpendingHistory : Fragment() {

    private var _binding: FragmentSpendingHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpendingHistoryViewModel by viewModels()

    private val averageMonthlyBill = 4500f // Average bill for each month

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpendingHistoryBinding.inflate(inflater, container, false)

        // Set up charts for last year and current year
        val chartLastYear: BarChart = binding.consumptionChartLastYear
        val chartCurrentYear: BarChart = binding.consumptionChartCurrentYear
        setupBarChart(chartLastYear, getLastYearData())
        setupBarChart(chartCurrentYear, getCurrentYearData())

        // Set up RecyclerView for spending history
        val recyclerView = binding.spendingRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = SpendingHistoryAdapter(getSpendingData())

        return binding.root
    }

    private fun setupBarChart(chart: BarChart, data: List<BarEntry>) {
        val dataSet = BarDataSet(data, "Monthly electricity bill").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
        }

        val barData = BarData(dataSet)
        chart.data = barData
        chart.description.text = "Istorija potrošnje"
        chart.animateY(1500) // Animation for Y-axis
    }

    private fun getLastYearData(): List<BarEntry> {
        // Bar chart data for last year, values relative to the average monthly bill
        return listOf(
            BarEntry(0f, 4200f),  // January
            BarEntry(1f, 4600f),  // February
            BarEntry(2f, 4800f),  // March
            BarEntry(3f, 4400f),  // April
            BarEntry(4f, 4700f),  // May
            BarEntry(5f, 4300f),  // June
            BarEntry(6f, 5100f),  // July
            BarEntry(7f, 4950f),  // August
            BarEntry(8f, 5000f),  // September
            BarEntry(9f, 4700f),  // October
            BarEntry(10f, 4600f), // November
            BarEntry(11f, 4800f)  // December
        )
    }

    private fun getCurrentYearData(): List<BarEntry> {
        // Bar chart data for the current year relative to the average bill of 4500 RSD
        return listOf(
            BarEntry(0f, 4550f),  // January
            BarEntry(1f, 4650f),  // February
            BarEntry(2f, 4500f),  // March
            BarEntry(3f, 4200f),  // April
            BarEntry(4f, 4500f),  // May
            BarEntry(5f, 4600f),   // June (up to current month)
        )
    }

    private fun getSpendingData(): List<SpendingItem> {
        // Adjusted spending data comparing each month to the average 4500 RSD
        return listOf(
            SpendingItem("January", "+50 RSD", 4550f, false),
            SpendingItem("February", "+150 RSD", 4650f, false),
            SpendingItem("March", "-200 RSD", 4500f, true),
            SpendingItem("April", "-300 RSD", 4200f, true),
            SpendingItem("May", "+300 RSD", 4500f, false),  // Exactly at the average
            SpendingItem("June", "+100 RSD", 4600f, false)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
