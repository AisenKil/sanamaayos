package com.example.finals

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

object EventRepository {

    private const val PREFS = "events_prefs"
    private const val KEY_EVENTS = "events_json"

    private var loaded = false
    private val events = mutableListOf<Event>()

    fun init(context: Context) {
        if (loaded) return
        loaded = true
        load(context)

        if (events.isEmpty()) {
            events.addAll(
                listOf(
                    Event("1", "Tech Summit 2026", "09/10/2026", "9:00 AM", "Auditorium", "A summit on the latest in tech."),
                    Event("2", "Career Fair", "09/15/2026", "10:00 AM", "Gymnasium", "Meet with top companies."),
                    Event("3", "Art Exhibit", "09/20/2026", "6:00 PM", "Fine Arts Building", "Featuring student work.")
                )
            )
            save(context)
        }
    }

    fun getAll(): List<Event> = events.toList()

    fun getByDate(date: String?): List<Event> {
        if (date.isNullOrBlank()) return getAll()
        return events.filter { it.date == date }
    }

    fun findById(id: String?): Event? {
        if (id.isNullOrBlank()) return null
        return events.find { it.id == id }
    }

    fun add(context: Context, event: Event) {
        events.add(0, event)
        save(context)
    }

    private fun save(context: Context) {
        val arr = JSONArray()
        for (e in events) {
            val o = JSONObject()
            o.put("id", e.id)
            o.put("title", e.title)
            o.put("date", e.date)
            o.put("time", e.time)
            o.put("location", e.location)
            o.put("description", e.description)
            arr.put(o)
        }
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_EVENTS, arr.toString())
            .apply()
    }

    private fun load(context: Context) {
        events.clear()
        val json = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getString(KEY_EVENTS, null) ?: return

        val arr = JSONArray(json)
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            events.add(
                Event(
                    id = o.getString("id"),
                    title = o.getString("title"),
                    date = o.getString("date"),
                    time = o.getString("time"),
                    location = o.getString("location"),
                    description = o.getString("description")
                )
            )
        }
    }
}