package com.example.finals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val loginButton = findViewById<Button>(R.id.login)
        val registerButton = findViewById<Button>(R.id.register)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        loginButton.setOnClickListener {
            // For now, we'll just navigate to the student home screen
            val intent = Intent(this, StudentHomeActivity::class.java)
            startActivity(intent)
        }

        registerButton.setOnClickListener {
            // For now, we'll just show a toast message
            Toast.makeText(this, "Register clicked", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        cancelButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
