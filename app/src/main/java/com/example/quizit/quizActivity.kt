package com.example.quizit

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Data class to hold quiz questions
    data class Question(val text: String, val answer: Boolean)

    private val questions = listOf(
        Question("The capital of France is Paris.", true),
        Question("Goldfish only have a three-second memory.", false),
        Question("A shark is the only fish that can blink with both eyes.", true),
        Question("Adding salt to water makes it boil significantly faster.", false),
        Question("Using a highlighter on a laptop screen can damage pixels.", true),
        Question("A wooden spoon over a pot prevents it from boiling over.", true),
        Question("Charging a phone in Airplane Mode is faster.", true),
        Question("Cracking your knuckles causes arthritis.", false),
        Question("Batteries last longer if stored in the freezer.", false),
        Question("A soda can tab is designed to hold a straw in place.", true)
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionText: TextView = findViewById(R.id.tvQuestion)
        val btnTruth: Button = findViewById(R.id.btnTrue)
        val btnFalse: Button = findViewById(R.id.btnFalse)
        val btnNextQuiz: Button = findViewById(R.id.btnNextQuiz)

        // Initially disable all buttons until countdown finishes
        setButtonsEnabled(false, btnTruth, btnFalse, btnNextQuiz)

        // Timer logic for countdown before starting the quiz
        object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000) + 1
                questionText.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                // Enable buttons and show the first question
                setButtonsEnabled(true, btnTruth, btnFalse, btnNextQuiz)
                updateQuestion(questionText)
            }
        }.start()

        // Truth Button Logic
        btnTruth.setOnClickListener {
            checkAnswer(true)
            setButtonsEnabled(false, btnTruth, btnFalse) // Prevent answering twice
        }

        // False Button Logic
        btnFalse.setOnClickListener {
            checkAnswer(false)
            setButtonsEnabled(false, btnTruth, btnFalse) // Prevent answering twice
        }

        // Next Button Logic
        btnNextQuiz.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                updateQuestion(questionText)
                setButtonsEnabled(true, btnTruth, btnFalse) // Re-enable for next question
            } else {
                navigateToScoreScreen()
            }
        }
    }

    private fun setButtonsEnabled(enabled: Boolean, vararg buttons: Button) {
        for (button in buttons) {
            button.isEnabled = enabled
        }
    }

    private fun updateQuestion(textView: TextView) {
        textView.text = questions[currentQuestionIndex].text
    }

    private fun checkAnswer(userPressedTrue: Boolean) {
        val correctAnswer = questions[currentQuestionIndex].answer

        if (userPressedTrue == correctAnswer) {
            score++
            Toast.makeText(this, "Correct! You're a superstar!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "That's an urban myth.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToScoreScreen() {
        val intent = Intent(this, scoreActivity::class.java)
        intent.putExtra("PLAYER_SCORE", score)
        intent.putExtra("TOTAL_QUESTIONS", questions.size)
        startActivity(intent)
        finish()
    }
}
