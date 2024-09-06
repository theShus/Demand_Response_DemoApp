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
            binding.searchDevicesButton.visibility = View.GONE

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
            DeviceItem(
                name = "Smart Doorbell",
                imageResId = R.drawable.baseline_doorbell_24,
                status = "Connected",
                powerDraw = "5 W",
                additionalInfo = "Battery Level: 85%\nLast Ring: 2 hours ago\nVideo Recording: Enabled",
                imageUrl = "https://www.younghouselove.com/wp-content/uploads/2024/03/Ring-Video-Doorbell-Installed-1024x722.jpg"
            ),
            DeviceItem(
                name = "Smart Lock",
                imageResId = R.drawable.baseline_lock_24,
                status = "Locked",
                powerDraw = "2 W",
                additionalInfo = "Battery Level: 90%\nLast Unlock: 4 hours ago\nAuto-Lock: Enabled\nGuest Access: Enabled",
                imageUrl = "https://media.architecturaldigest.com/photos/5d9cca74184154000864c0cf/16:9/w_1920,c_limit/Screen%20Shot%202019-10-08%20at%201.41.47%20PM.png"
            ),
        )
    }

    private fun returnDeviceToFragment(device: DeviceItem) {
        // Return the selected device back to the SmartDevicesFragment
        val resultIntent = Intent()
        resultIntent.putExtra("name", device.name)
        resultIntent.putExtra("imageResId", device.imageResId)
        resultIntent.putExtra("status", device.status)
        resultIntent.putExtra("powerDraw", device.powerDraw)
        resultIntent.putExtra("additionalInfo", device.additionalInfo)
        resultIntent.putExtra("imageUrl", device.imageUrl)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}