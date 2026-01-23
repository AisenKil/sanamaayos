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

        val titleEditText = findViewById<EditText>(R.id.eventTitle)
        val dateEditText = findViewById<EditText>(R.id.eventDate)
        val timeEditText = findViewById<EditText>(R.id.eventTime)
        val locationEditText = findViewById<EditText>(R.id.eventLocation)
        val descriptionEditText = findViewById<EditText>(R.id.eventDescription)
        val saveEventButton = findViewById<Button>(R.id.saveEventButton)

        val selectedDate = intent.getStringExtra("SELECTED_DATE")
        dateEditText.setText(selectedDate)
        dateEditText.isEnabled = false // Makes it non-editable

        saveEventButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val date = dateEditText.text.toString()
            val time = timeEditText.text.toString()
            val location = locationEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (title.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty() && location.isNotEmpty() && description.isNotEmpty()) {
                val newEvent = Event(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    date = date,
                    time = time,
                    location = location,
                    description = description
                )
                Event.events.add(0, newEvent) // Add to the top of the list
                finish() // Go back to the previous screen
            }
        }
    }
}
