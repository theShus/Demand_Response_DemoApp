package master.app.ui.smartDevices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import master.app.databinding.ActivityDeviceDetailsBinding

class DeviceDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeviceDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeviceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get device name from intent
        val deviceName = intent.getStringExtra("deviceName")
        binding.deviceNameText.text = deviceName

        // Here you can add more information about the device
    }
}
