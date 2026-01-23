package com.example.finals

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        val eventId = intent.getStringExtra("EVENT_ID")
        val event = Event.events.find { it.id == eventId }

        if (event != null) {
            val titleTextView = findViewById<TextView>(R.id.eventTitle)
            val dateTextView = findViewById<TextView>(R.id.eventDate)
            val timeTextView = findViewById<TextView>(R.id.eventTime)
            val locationTextView = findViewById<TextView>(R.id.eventLocation)
            val descriptionTextView = findViewById<TextView>(R.id.eventDescription)

            titleTextView.text = event.title
            dateTextView.text = event.date
            timeTextView.text = event.time
            locationTextView.text = event.location
            descriptionTextView.text = event.description
        }
    }
}
