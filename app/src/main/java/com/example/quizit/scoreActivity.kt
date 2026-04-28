package com.example.quizit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class scoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val tvFinalScore: TextView = findViewById(R.id.tvFinalScore)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        // Retrieve the score from the intent
        val score = intent.getIntExtra("PLAYER_SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 10)

        // Display the score
        tvFinalScore.text = "$score / $totalQuestions"

        // Restart button logic
        btnRestart.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
