package master.app.ui.smartDevices

import DeviceItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import master.app.R

class SmartDevicesViewModel : ViewModel() {

    private val _devices = MutableLiveData<MutableList<DeviceItem>>().apply {
        value = mutableListOf(
            DeviceItem(
                name = "Smart AC",
                imageResId = R.drawable.baseline_ac_unit_24,
                status = "Connected",
                powerDraw = "1500 W",
                additionalInfo = "Current Set Temperature: 22°C\nFan Speed: Medium\nOperating Mode: Cool\nEnergy-saving mode: Off",
                imageUrl = "https://www.thespruce.com/thmb/OnB2bQGk2Lm24v0yum4aiKdvDPQ=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/how-types-of-air-conditioning-systems-work-1824734-ductless-460683a6b30140cf966bfbe91ed30a78.jpg"
            ),
            DeviceItem(
                name = "Smart Bulb (Living Room)",
                imageResId = R.drawable.baseline_lightbulb_24,
                status = "Connected",
                powerDraw = "9 W",
                additionalInfo = "Color: Warm White\nBrightness Level: 75%\nSchedule: On from 6 PM to 11 PM",
                imageUrl = "https://helios-i.mashable.com/imagery/articles/05tNJRv9xHarvNrpjK3tyzY/hero-image.fill.size_1248x702.v1623388579.jpg"
            ),
            DeviceItem(
                name = "Smart Thermostat",
                imageResId = R.drawable.baseline_device_thermostat_24,
                status = "Disconnected",
                powerDraw = "Idle",
                additionalInfo = "Target Temperature: 20°C\nCurrent Temperature: 19°C\nMode: Heating\nLast Connection: 3 hours ago",
                imageUrl = "https://www.bobvila.com/wp-content/uploads/2019/02/Smart_Thermostat.jpg"
            ),
            DeviceItem(
                name = "Smart Fridge",
                imageResId = R.drawable.baseline_kitchen_24,
                status = "Connected",
                powerDraw = "250 W",
                additionalInfo = "Temperature: 3°C (Fridge)\n-18°C (Freezer)\nEnergy Efficiency Mode: On\nWater Filter Status: Good\nDoor Ajar Notification: Disabled",
                imageUrl = "https://www.cnet.com/a/img/resize/7fdeb9b4eda1aefc8b6e1c77adb426694585e9f1/hub/2016/05/03/2f31e0ae-4c9b-4f23-94ac-705f2bda32a8/samsungsmartfridgeproductphotos-9.jpg?auto=webp&width=1920"
            )
        )


    }

    val devices: LiveData<MutableList<DeviceItem>> = _devices

    fun updateDeviceStatus(deviceName: String, newStatus: String) {
        _devices.value?.let { devices ->
            val deviceIndex = devices.indexOfFirst { it.name == deviceName }  // Find the index of the device with the given name
            if (deviceIndex != -1) {
                val updatedDevice = devices[deviceIndex].copy(status = newStatus)
                devices[deviceIndex] = updatedDevice
                _devices.value = devices  // Trigger LiveData update
            }
        }
    }

    fun removeDevice(deviceName: String) {
        _devices.value?.let { devices ->
            // Create a new list and copy the current devices into it
            val newList = devices.toMutableList()
            // Remove the device by name
            newList.removeAll { it.name == deviceName }
            // Set the new list back into the LiveData
            _devices.value = newList
        }
    }



    // Function to add a new device
    fun addDevice(device: DeviceItem) {
        _devices.value?.add(device)
        _devices.value = _devices.value // Trigger LiveData update
    }
}
