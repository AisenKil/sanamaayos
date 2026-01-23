package com.example.finals

data class Event(
    val id: String,
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    val description: String
) {
    companion object {
        val events = mutableListOf<Event>(
            Event("1", "Tech Summit 2026", "09/10/2026", "9:00 AM", "Auditorium", "A summit on the latest in tech."),
            Event("2", "Career Fair", "09/15/2026", "10:00 AM", "Gymnasium", "Meet with top companies."),
            Event("3", "Art Exhibit", "09/20/2026", "6:00 PM", "Fine Arts Building", "Featuring student work.")
        )
    }
}
