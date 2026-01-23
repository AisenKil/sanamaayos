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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AdminHomeActivity : AppCompatActivity(), EventAdapter.OnItemClickListener {

    private lateinit var eventsRecyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var calendarView: CalendarView
    private lateinit var noEventsTextView: TextView
    private var selectedDate: String? = null
    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_home)

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
            selectedDate = dateFormat.format(calendar.time)
            updateEventList()
        }

        val addEventButton = findViewById<FloatingActionButton>(R.id.addEventButton)
        addEventButton.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            val dateToSend = selectedDate ?: dateFormat.format(Calendar.getInstance().time)
            intent.putExtra("SELECTED_DATE", dateToSend)
            startActivity(intent)
        }
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
        // TODO: Handle item click
    }
}
