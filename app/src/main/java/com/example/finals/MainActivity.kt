package com.example.finals

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the role cards
        val adminCard = findViewById<CardView>(R.id.adminCard)
        val studentCard = findViewById<CardView>(R.id.studentCard)

        // Set click listeners
        adminCard.setOnClickListener {
            // Navigate to AdminActivity
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
        }

        studentCard.setOnClickListener {
            // Navigate to StudentActivity
            val intent = Intent(this, StudentActivity::class.java)
            startActivity(intent)
        }

        // Apply fade-in animation
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val mainLayout = findViewById<ConstraintLayout>(R.id.main)
        mainLayout.startAnimation(fadeIn)
    }
}
