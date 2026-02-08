package com.example.finals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(
    private var events: List<Event>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.eventTitle)
        private val dateTextView: TextView = itemView.findViewById(R.id.eventDate)
        private val timeTextView: TextView = itemView.findViewById(R.id.eventTime)
        private val locationTextView: TextView = itemView.findViewById(R.id.eventLocation)

        init {
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) listener.onItemClick(events[pos])
            }
        }

        fun bind(event: Event) {
            titleTextView.text = event.title
            dateTextView.text = event.date
            timeTextView.text = event.time
            locationTextView.text = event.location
        }
    }

    interface OnItemClickListener {
        fun onItemClick(event: Event)
    }
}
