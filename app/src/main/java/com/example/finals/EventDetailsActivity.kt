package com.example.finals

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)

        EventRepository.init(this)

        val eventId = intent.getStringExtra("EVENT_ID")
        val event = EventRepository.findById(eventId) ?: run {
            finish()
            return
        }

        findViewById<TextView>(R.id.eventTitle).text = event.title
        findViewById<TextView>(R.id.eventDate).text = event.date
        findViewById<TextView>(R.id.eventTime).text = event.time
        findViewById<TextView>(R.id.eventLocation).text = event.location
        findViewById<TextView>(R.id.eventDescription).text = event.description
    }
}

