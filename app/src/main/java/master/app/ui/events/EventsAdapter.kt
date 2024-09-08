package master.app.ui.events

import DREvent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import master.app.R

class EventsAdapter(private val eventsList: List<DREvent>) :
    RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_item, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = eventsList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = eventsList.size

    inner class EventsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        private val eventDate: TextView = itemView.findViewById(R.id.eventDate)
        private val expandArrow: ImageView = itemView.findViewById(R.id.expand_arrow)
        private val collapsedView: View = itemView.findViewById(R.id.collapsed_view)
        private val expandedView: View = itemView.findViewById(R.id.expanded_view)
        private val registerButton: Button = itemView.findViewById(R.id.registerButton)

        fun bind(event: DREvent) {
            // Set the collapsed view data
            eventTitle.text = event.title
            eventDate.text = event.date

            // Set the expanded view data
            itemView.findViewById<TextView>(R.id.eventStatus).text = event.status
            itemView.findViewById<TextView>(R.id.eventStartDate).text = "Start Date: ${event.startDate}"
            itemView.findViewById<TextView>(R.id.eventEndDate).text = "End Date: ${event.endDate}"
            itemView.findViewById<TextView>(R.id.eventStartTime).text = "Start Time: ${event.startTime}"
            itemView.findViewById<TextView>(R.id.eventEndTime).text = "End Time: ${event.endTime}"
            itemView.findViewById<TextView>(R.id.eventCounty).text = "County: ${event.county}"

            // Set the button state based on isRegistered
            updateRegisterButtonState(event.isRegistered)

            // Toggle the expand/collapse of the view
            var isExpanded = false
            collapsedView.setOnClickListener {
                isExpanded = !isExpanded
                expandedView.visibility = if (isExpanded) View.VISIBLE else View.GONE
                expandArrow.setImageResource(
                    if (isExpanded) R.drawable.baseline_arrow_drop_down_24
                    else R.drawable.baseline_arrow_forward_ios_24
                )
            }

            // Handle register button click
            registerButton.setOnClickListener {
                event.isRegistered = true
                updateRegisterButtonState(true)
            }
        }

        private fun updateRegisterButtonState(isRegistered: Boolean) {
            if (isRegistered) {
                registerButton.text = "Registered"
                registerButton.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.holo_green_dark))
                registerButton.isEnabled = false
            } else {
                registerButton.text = "Register"
                registerButton.setBackgroundColor(ContextCompat.getColor(itemView.context, android.R.color.holo_blue_light))
                registerButton.isEnabled = true
            }
        }
    }
}
