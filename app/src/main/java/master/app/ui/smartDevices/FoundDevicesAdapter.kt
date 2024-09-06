package master.app.ui.smartDevices

import DeviceItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import master.app.R

class FoundDevicesAdapter(private val devicesList: List<DeviceItem>, private val onDeviceSelected: (DeviceItem) -> Unit) :
    RecyclerView.Adapter<FoundDevicesAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devicesList[position]
        holder.bind(device, onDeviceSelected)
    }

    override fun getItemCount(): Int = devicesList.size

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val deviceName: TextView = itemView.findViewById(R.id.deviceName)
        private val deviceStatus: TextView = itemView.findViewById(R.id.deviceStatus)
        private val moreInfoButton: Button = itemView.findViewById(R.id.moreInfoButton)

        fun bind(deviceItem: DeviceItem, onDeviceSelected: (DeviceItem) -> Unit) {
//            deviceImage.setImageResource(deviceItem.imageUrl) todo stavi sliku
            deviceName.text = deviceItem.name
            deviceStatus.text = deviceItem.status

            // When the user clicks "Connect", send the device back to the main list
            moreInfoButton.text = "Add"
            moreInfoButton.setOnClickListener {
                onDeviceSelected(deviceItem)
            }
        }
    }
}
