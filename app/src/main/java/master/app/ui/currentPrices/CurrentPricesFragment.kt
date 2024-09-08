package master.app.ui.currentPrices

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import master.app.databinding.FragmentCurrentPricesBinding

class CurrentPricesFragment : Fragment() {

    private var _binding: FragmentCurrentPricesBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentPricesViewModel: CurrentPricesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentPricesViewModel = ViewModelProvider(this).get(CurrentPricesViewModel::class.java)


        _binding = FragmentCurrentPricesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up chart
        val chart: BarChart = binding.electricityPriceChart
        setupChart(chart)

        // Observe the chart data from ViewModel
        currentPricesViewModel.chartEntries.observe(viewLifecycleOwner, Observer { entries ->
            val dataSet = BarDataSet(entries, "Cena električne energije (din/kWh)").apply {
                colors = listOf(Color.GREEN, Color.BLUE, Color.RED) // Static colors based on zones
                valueTextColor = Color.BLACK
                valueTextSize = 14f
            }

            val barData = BarData(dataSet)
            chart.data = barData
            chart.invalidate()  // Refresh the chart with new data
        })

        // Observe the price info from ViewModel (optional)
        currentPricesViewModel.greenZoneInfo.observe(viewLifecycleOwner, Observer { info ->
            binding.greenZoneDescription.text = info
        })

        currentPricesViewModel.blueZoneInfo.observe(viewLifecycleOwner, Observer { info ->
            binding.blueZoneDescription.text = info
        })

        currentPricesViewModel.redZoneInfo.observe(viewLifecycleOwner, Observer { info ->
            binding.redZoneDescription.text = info
        })


        return root
    }

    private fun setupChart(chart: BarChart) {
        chart.description.text = "Cena po zoni"
        chart.animateY(1500)  // Animation for Y-axis
        chart.setFitBars(true)  // Make bars fit the chart
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
