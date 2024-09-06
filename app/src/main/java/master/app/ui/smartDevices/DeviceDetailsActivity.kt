package master.app.ui.smartDevices

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import master.app.databinding.ActivityDeviceDetailsBinding

class DeviceDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceDetailsBinding
    private val smartDevicesViewModel: SmartDevicesViewModel by viewModels()  // ViewModel to manage device list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get device data from the intent
        val name = intent.getStringExtra("name") ?: ""
        val status = intent.getStringExtra("status") ?: ""
        val powerDraw = intent.getStringExtra("powerDraw") ?: ""
        val additionalInfo = intent.getStringExtra("additionalInfo") ?: ""
        val imageUrl = intent.getStringExtra("imageUrl") ?: ""

        // Set the device name at the top
        binding.deviceNameText.text = name

        // Load the device image using Glide (from the URL passed through the intent)
        Glide.with(this)
            .load(imageUrl)
            .into(binding.deviceImageView)

        // Set device status with color (green for ON, red for OFF)
        binding.deviceStatusText.text = "Status: $status"
        binding.deviceStatusText.setTextColor(
            if (status.equals("Connected", ignoreCase = true)) Color.GREEN else Color.RED
        )

        // Set device-specific information
        binding.devicePowerDrawText.text = "Average Power Draw: $powerDraw"
        binding.deviceAdditionalInfoText.text = additionalInfo


        binding.turnOffButton.setOnClickListener {
            if (status == "Disconnected"){
                smartDevicesViewModel.updateDeviceStatus(name, "Connected")
                binding.deviceStatusText.text = "Status: Connected"
                binding.deviceStatusText.setTextColor(Color.GREEN)
                Toast.makeText(this, "$name has been turned off", Toast.LENGTH_SHORT).show()
            }else{
                smartDevicesViewModel.updateDeviceStatus(name, "Disconnected")
                binding.deviceStatusText.text = "Status: Disconnected"
                binding.deviceStatusText.setTextColor(Color.RED)
                Toast.makeText(this, "$name has been turned off", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
