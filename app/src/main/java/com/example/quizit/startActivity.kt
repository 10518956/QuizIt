package com.example.quizit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomePage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Declaration for button
        val startButton = findViewById<Button>(R.id.btnStartQuiz)

        // Logic for the button
        startButton.setOnClickListener {
            // FIX: Changed 'quizActivity' to 'QuizActivity' (Capitalized)
            // Ensure you have a file named QuizActivity.kt in your project
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}