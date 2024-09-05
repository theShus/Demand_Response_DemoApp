package master.app.ui.smartDevices

import DeviceItem
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import master.app.R
import master.app.databinding.ActivityAddDeviceBinding


class AddDeviceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDeviceBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDeviceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView for found devices
        binding.foundDevicesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Search button click listener
        binding.searchDevicesButton.setOnClickListener {
            performDeviceSearch()
        }
    }

    private fun performDeviceSearch() {
        // Show progress bar and status text
        binding.searchProgressBar.visibility = View.VISIBLE
        binding.searchStatusText.visibility = View.VISIBLE
        binding.searchStatusText.text = "Searching for devices..."

        // Simulate a 3-second search with a delay
        handler.postDelayed({
            // Hide progress bar after the search is complete
            binding.searchProgressBar.visibility = View.GONE
            binding.searchStatusText.visibility = View.GONE

            // Show the list of found devices
            binding.foundDevicesRecyclerView.visibility = View.VISIBLE
            binding.foundDevicesRecyclerView.adapter = FoundDevicesAdapter(getFoundDevices()) { device ->
                returnDeviceToFragment(device)
            }

        }, 3000) // 3 seconds delay to simulate search
    }

    private fun getFoundDevices(): List<DeviceItem> {
        // Simulate found devices
        return listOf(
            DeviceItem("Pametna sijalica", R.drawable.baseline_lightbulb_24, "Ready to connect"),
            DeviceItem("Pametni termostat", R.drawable.baseline_device_thermostat_24, "Ready to connect"),
            DeviceItem("Klima uređaj", R.drawable.baseline_ac_unit_24, "Ready to connect")
        )
    }

    private fun returnDeviceToFragment(device: DeviceItem) {
        // Return the selected device back to the SmartDevicesFragment
        val resultIntent = Intent()
        resultIntent.putExtra("deviceName", device.name)
        resultIntent.putExtra("deviceStatus", device.status)
        resultIntent.putExtra("deviceImage", device.imageResId)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}