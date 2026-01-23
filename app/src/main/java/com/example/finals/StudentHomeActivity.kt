package com.example.finals

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StudentHomeActivity : AppCompatActivity(), EventAdapter.OnItemClickListener {

    private lateinit var eventsRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var calendarView: CalendarView
    private lateinit var noEventsTextView: TextView
    private var selectedDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)

        val backButton = findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        calendarView = findViewById(R.id.calendarView)
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView)
        noEventsTextView = findViewById(R.id.noEventsTextView)
        eventsRecyclerView.layoutManager = LinearLayoutManager(this)

        eventAdapter = EventAdapter(Event.events, this)
        eventsRecyclerView.adapter = eventAdapter

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, dayOfMonth)
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            selectedDate = sdf.format(calendar.time)
            updateEventList()
        }

        updateEventList()
    }

    override fun onResume() {
        super.onResume()
        updateEventList()
    }

    private fun updateEventList() {
        val eventsToShow = if (selectedDate != null) {
            Event.events.filter { it.date == selectedDate }
        } else {
            Event.events
        }

        eventAdapter.updateEvents(eventsToShow)

        if (eventsToShow.isEmpty()) {
            noEventsTextView.visibility = View.VISIBLE
            eventsRecyclerView.visibility = View.GONE
        } else {
            noEventsTextView.visibility = View.GONE
            eventsRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun onItemClick(event: Event) {
        val intent = Intent(this, EventDetailsActivity::class.java).apply {
            putExtra("EVENT_ID", event.id)
        }
        startActivity(intent)
    }
}
