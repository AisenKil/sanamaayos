package com.example.finals

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.UUID

class AddEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        EventRepository.init(this)

        val titleEditText = findViewById<EditText>(R.id.eventTitle)
        val dateEditText = findViewById<EditText>(R.id.eventDate)
        val timeEditText = findViewById<EditText>(R.id.eventTime)
        val locationEditText = findViewById<EditText>(R.id.eventLocation)
        val descriptionEditText = findViewById<EditText>(R.id.eventDescription)
        val saveEventButton = findViewById<Button>(R.id.saveEventButton)

        val selectedDate = intent.getStringExtra("SELECTED_DATE").orEmpty()
        dateEditText.setText(selectedDate)
        dateEditText.isEnabled = false

        saveEventButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val date = dateEditText.text.toString().trim()
            val time = timeEditText.text.toString().trim()
            val location = locationEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            if (title.isEmpty() || date.isEmpty() || time.isEmpty() || location.isEmpty() || description.isEmpty()) {
                return@setOnClickListener
            }

            EventRepository.add(
                this,
                Event(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    date = date,
                    time = time,
                    location = location,
                    description = description
                )
            )

            NotificationHelper.showAnnouncement(
                this,
                "New Announcement",
                title
            )

            finish()
        }
    }
}
