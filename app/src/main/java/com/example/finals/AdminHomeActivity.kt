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

        EventRepository.init(this)

        findViewById<ImageButton>(R.id.backButton).setOnClickListener { finish() }

        calendarView = findViewById(R.id.calendarView)
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView)
        noEventsTextView = findViewById(R.id.noEventsTextView)

        eventsRecyclerView.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter(emptyList(), this)
        eventsRecyclerView.adapter = eventAdapter

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance().apply { set(year, month, dayOfMonth) }
            selectedDate = dateFormat.format(cal.time)
            updateEventList()
        }

        findViewById<FloatingActionButton>(R.id.addEventButton).setOnClickListener {
            val dateToSend = selectedDate ?: dateFormat.format(Calendar.getInstance().time)
            startActivity(Intent(this, AddEventActivity::class.java).putExtra("SELECTED_DATE", dateToSend))
        }

        updateEventList()
    }

    override fun onResume() {
        super.onResume()
        updateEventList()
    }

    private fun updateEventList() {
        val eventsToShow = EventRepository.getByDate(selectedDate)
        eventAdapter.updateEvents(eventsToShow)

        val hasEvents = eventsToShow.isNotEmpty()
        noEventsTextView.visibility = if (hasEvents) View.GONE else View.VISIBLE
        eventsRecyclerView.visibility = if (hasEvents) View.VISIBLE else View.GONE
    }

    override fun onItemClick(event: Event) {
        startActivity(Intent(this, EventDetailsActivity::class.java).putExtra("EVENT_ID", event.id))
    }
}

