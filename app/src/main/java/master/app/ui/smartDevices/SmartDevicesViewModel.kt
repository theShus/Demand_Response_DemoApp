package master.app.ui.smartDevices

import DeviceItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import master.app.R

class SmartDevicesViewModel : ViewModel() {

    private val _devices = MutableLiveData<MutableList<DeviceItem>>().apply {
        value = mutableListOf(
            DeviceItem("Smart ac", R.drawable.baseline_ac_unit_24, "Connected"),
            DeviceItem("Smart bulb living room", R.drawable.baseline_lightbulb_24, "Connected"),
            DeviceItem("Smart termostat", R.drawable.baseline_device_thermostat_24, "Disconnected")
        )
    }
    val devices: LiveData<MutableList<DeviceItem>> = _devices

    // Function to add a new device
    fun addDevice(device: DeviceItem) {
        _devices.value?.add(device)
        _devices.value = _devices.value // Trigger LiveData update
    }
}
