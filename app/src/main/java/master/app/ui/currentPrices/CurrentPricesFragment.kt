package master.app.ui.currentPrices

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import master.app.databinding.FragmentCurrentPricesBinding

class CurrentPricesFragment : Fragment() {

    private var _binding: FragmentCurrentPricesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val currentPricesViewModel = ViewModelProvider(this).get(CurrentPricesViewModel::class.java)

        _binding = FragmentCurrentPricesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up chart
        val chart: LineChart = binding.electricityPriceChart
        setupChart(chart)

        return root
    }

    private fun setupChart(chart: LineChart) {
        // Example data entries (in a real-world scenario, retrieve this data dynamically)
        val entries = listOf(
            Entry(0f, 50f), // x: time in hours, y: price in currency units
            Entry(1f, 52f),
            Entry(2f, 48f),
            Entry(3f, 60f),
            Entry(4f, 55f),
            Entry(5f, 70f)
        )

        val dataSet = LineDataSet(entries, "Cena električne energije (RSD/kWh)").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            lineWidth = 2f
            setDrawCircles(true)
            setCircleColor(Color.RED)
        }

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.description.text = "Trenutna cena električne energije"
        chart.animateX(1500) // Animation for X-axis
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
