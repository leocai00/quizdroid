package edu.washington.wanxic.quizdroid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Answer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val numOfCorrect = intent.getIntExtra("numOfCorrect", 0)
        val question = intent.getParcelableExtra<Question>("question")
        val currentAnswer = intent.getStringExtra("currentAnswer")
        val count = intent.getIntExtra("count", 0)
        val message = intent.getStringExtra("message")

        val currentAnswerText = findViewById<TextView>(R.id.currentAnswer)
        val correctAnswerText = findViewById<TextView>(R.id.correctAnswer)
        val ratio =  findViewById<TextView>(R.id.ratio)
        val next = findViewById<Button>(R.id.next)

        currentAnswerText.text = "Your answer: " + currentAnswer
        correctAnswerText.text = "Corrent answer: " + question.options[question.answer]
        ratio.text = "You have $numOfCorrect out of 3 corrrent"
        if(count == 3) next.text = "Finish"

        next.setOnClickListener {
            if(count < 3) {
                val intent = Intent(this, Quiz::class.java)
                intent.putExtra("count", count)
                intent.putExtra("keyIdentifier", message)
                intent.putExtra("numOfCorrect", numOfCorrect)
                startActivity(intent)
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
