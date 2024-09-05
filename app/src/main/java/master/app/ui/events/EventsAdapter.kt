package master.app.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        private val eventDescription: TextView = itemView.findViewById(R.id.eventDescription)
        private val registerButton: Button = itemView.findViewById(R.id.registerButton)

        fun bind(event: DREvent) {
            eventTitle.text = event.title
            eventDate.text = event.date
            eventDescription.text = event.description

            // Handle the registration button click
            registerButton.setOnClickListener {
                // Logic to register the user for the event
                registerButton.text = "Registered"
                registerButton.isEnabled = false
            }
        }
    }
}
