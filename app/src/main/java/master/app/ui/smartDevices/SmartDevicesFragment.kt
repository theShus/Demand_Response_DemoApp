package master.app.ui.smartDevices

import DeviceItem
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import master.app.R
import master.app.databinding.FragmentSmartDevicesBinding

class SmartDevicesFragment : Fragment() {

    private var _binding: FragmentSmartDevicesBinding? = null
    private val binding get() = _binding!!

    private val smartDevicesViewModel: SmartDevicesViewModel by viewModels()

    companion object {
        const val ADD_DEVICE_REQUEST_CODE = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmartDevicesBinding.inflate(inflater, container, false)

        // Setup RecyclerView for devices
        val recyclerView = binding.devicesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe the LiveData from the ViewModel and update the RecyclerView when devices are added
        smartDevicesViewModel.devices.observe(viewLifecycleOwner) { deviceList ->
            recyclerView.adapter = SmartDevicesAdapter(deviceList)
        }

        // Setup FAB for adding a new device
        binding.addDeviceFab.setOnClickListener {
            val intent = Intent(requireContext(), AddDeviceActivity::class.java)
            startActivityForResult(intent, ADD_DEVICE_REQUEST_CODE)
        }

        return binding.root
    }

    // Handle the result from AddDeviceActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_DEVICE_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            // Get device information from the result intent
            val name = data?.getStringExtra("name") ?: ""
            val status = data?.getStringExtra("status") ?: ""
            val imageResId = data?.getIntExtra("imageResId", R.drawable.baseline_add_24) ?: 0
            val powerDraw = data?.getStringExtra("powerDraw") ?: ""
            val additionalInfo = data?.getStringExtra("additionalInfo") ?: ""
            val imageUrl = data?.getStringExtra("imageUrl") ?: ""

//             Add the new device to the ViewModel
            val newDevice = DeviceItem(name, imageResId, status, powerDraw, additionalInfo, imageUrl)
            smartDevicesViewModel.addDevice(newDevice)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
