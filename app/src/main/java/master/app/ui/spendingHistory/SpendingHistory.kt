package master.app.ui.spendingHistory

import SpendingItem
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import master.app.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import master.app.databinding.FragmentSpendingHistoryBinding

class SpendingHistory : Fragment() {

    private var _binding: FragmentSpendingHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SpendingHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpendingHistoryBinding.inflate(inflater, container, false)

        // Set up chart
        val chart: LineChart = binding.consumptionChart
        setupChart(chart)

        // Set up RecyclerView for spending history
        val recyclerView = binding.spendingRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = SpendingHistoryAdapter(getSpendingData())

        return binding.root
    }

    private fun setupChart(chart: LineChart) {
        // Example data entries for energy consumption
        val entries = listOf(
            Entry(0f, 100f), // Day 1 consumption
            Entry(1f, 90f),
            Entry(2f, 110f),
            Entry(3f, 80f),
            Entry(4f, 95f),
            Entry(5f, 120f)
        )

        val dataSet = LineDataSet(entries, "Potrošnja energije (kWh)").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            lineWidth = 2f
            setDrawCircles(true)
            setCircleColor(Color.GREEN)
        }

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.description.text = "Istorija potrošnje"
        chart.animateX(1500) // Animation for X-axis
    }

    private fun getSpendingData(): List<SpendingItem> {
        // Example spending data
        return listOf(
            SpendingItem("January", "+500 RSD", true),
            SpendingItem("February", "-200 RSD", false),
            SpendingItem("March", "+300 RSD", true),
            SpendingItem("April", "-100 RSD", false),
            SpendingItem("May", "+600 RSD", true)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
