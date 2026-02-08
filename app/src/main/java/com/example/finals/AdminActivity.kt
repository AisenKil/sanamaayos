package com.example.finals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val loginButton = findViewById<Button>(R.id.login)
        val registerButton = findViewById<Button>(R.id.register)
        val backButton = findViewById<ImageButton>(R.id.backButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        loginButton.setOnClickListener {
            startActivity(Intent(this, AdminHomeActivity::class.java))
            finish()
        }

        registerButton.setOnClickListener {
            Toast.makeText(this, "Register clicked", Toast.LENGTH_SHORT).show()
        }

        backButton.setOnClickListener { finish() }
        cancelButton.setOnClickListener { finish() }
    }
}

