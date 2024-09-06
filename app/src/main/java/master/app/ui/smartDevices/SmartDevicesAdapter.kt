package master.app.ui.smartDevices

import DeviceItem
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import master.app.R

class SmartDevicesAdapter(private val devicesList: List<DeviceItem>) :
    RecyclerView.Adapter<SmartDevicesAdapter.DeviceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.device_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devicesList[position]
        holder.bind(device)
    }

    override fun getItemCount(): Int = devicesList.size

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val deviceImage: ImageView = itemView.findViewById(R.id.deviceImage)
        private val deviceName: TextView = itemView.findViewById(R.id.deviceName)
        private val deviceStatus: TextView = itemView.findViewById(R.id.deviceStatus)
        private val moreInfoButton: Button = itemView.findViewById(R.id.moreInfoButton)

        fun bind(deviceItem: DeviceItem) {
            deviceImage.setImageResource(deviceItem.imageResId)
            deviceName.text = deviceItem.name
            deviceStatus.text = deviceItem.status

            // Handle "More Info" button click
            moreInfoButton.setOnClickListener {
                val intent = Intent(itemView.context, DeviceDetailsActivity::class.java)
               intent.putExtra("name", deviceItem.name)
               intent.putExtra("imageResId", deviceItem.imageResId)
               intent.putExtra("status", deviceItem.status)
               intent.putExtra("powerDraw", deviceItem.powerDraw)
               intent.putExtra("additionalInfo", deviceItem.additionalInfo)
               intent.putExtra("imageUrl", deviceItem.imageUrl)
                itemView.context.startActivity(intent)
            }
        }
    }
}
