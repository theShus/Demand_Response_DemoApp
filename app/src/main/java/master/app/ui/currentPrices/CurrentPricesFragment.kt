package master.app.ui.currentPrices

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
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
        val chart: BarChart = binding.electricityPriceChart
        setupChart(chart)

        // Update the combined text with colored zones
        val combinedText = """
            Zelena zona: 6.034 din/kWh
                Radnim danima i subotom:
                - 00:00 do 06:00
                Nedeljom važi 24 sata.

            Plava zona: 9.051 din/kWh
                Radnim danima i subotom:
                - 08:00 do 16:00
                - 22:00 do 24:00.

            Crvena zona: 18.102 din/kWh
                Radnim danima i subotom:
                - 06:00 do 08:00
                - 16:00 do 22:00.
        """.trimIndent()

        // Create a SpannableString for styling specific parts of the text
// Create a SpannableString for styling specific parts of the text
        val spannableString = SpannableString(combinedText)

// Apply color, size, and bold spans to zone names
        val greenZoneStart = combinedText.indexOf("Zelena zona")
        val blueZoneStart = combinedText.indexOf("Plava zona")
        val redZoneStart = combinedText.indexOf("Crvena zona")

// Green Zone
        spannableString.setSpan(
            ForegroundColorSpan(Color.GREEN),
            greenZoneStart, greenZoneStart + "Zelena zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            RelativeSizeSpan(1.5f), // 50% larger
            greenZoneStart, greenZoneStart + "Zelena zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), // Bold text
            greenZoneStart, greenZoneStart + "Zelena zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

// Blue Zone
        spannableString.setSpan(
            ForegroundColorSpan(Color.BLUE),
            blueZoneStart, blueZoneStart + "Plava zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            RelativeSizeSpan(1.5f), // 50% larger
            blueZoneStart, blueZoneStart + "Plava zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), // Bold text
            blueZoneStart, blueZoneStart + "Plava zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

// Red Zone
        spannableString.setSpan(
            ForegroundColorSpan(Color.RED),
            redZoneStart, redZoneStart + "Crvena zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            RelativeSizeSpan(1.5f), // 50% larger
            redZoneStart, redZoneStart + "Crvena zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD), // Bold text
            redZoneStart, redZoneStart + "Crvena zona".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

// Set the styled text in the TextView
        binding.combinedZoneInfo.text = spannableString
        return root
    }

    private fun setupChart(chart: BarChart) {
        // Data for Green, Blue, and Red zones from the table you provided
        val entries = listOf(
            BarEntry(0f, 6.034f),  // Green Zone (Jednotarifno)
            BarEntry(1f, 9.051f),  // Blue Zone (Jednotarifno)
            BarEntry(2f, 18.102f)  // Red Zone (Jednotarifno)
        )

        val dataSet = BarDataSet(entries, "Cena električne energije (din/kWh)").apply {
            colors = listOf(Color.GREEN, Color.BLUE, Color.RED)
            valueTextColor = Color.BLACK
            valueTextSize = 14f
        }

        val barData = BarData(dataSet)
        chart.data = barData
        chart.description.text = "Cena po zoni"
        chart.animateY(1500) // Animation for Y-axis
        chart.setFitBars(true) // Make bars fit the chart
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
