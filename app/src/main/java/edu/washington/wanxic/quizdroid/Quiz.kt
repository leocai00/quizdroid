package edu.washington.wanxic.quizdroid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class Quiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val message = intent.getStringExtra("keyIdentifier")
        var count = intent.getIntExtra("count", 0)
        var numOfCorrect = intent.getIntExtra("numOfCorrect", 0)
        Log.i("Quiz", count.toString())

        val textView = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val answer1 = findViewById<RadioButton>(R.id.radioButton1)
        val answer2 = findViewById<RadioButton>(R.id.radioButton2)
        val answer3 = findViewById<RadioButton>(R.id.radioButton3)
        val answer4 = findViewById<RadioButton>(R.id.radioButton4)
        val submit = findViewById<Button>(R.id.submit)
        submit.isClickable = false

        var question : Question
        if(message == "Math") {
            when(count) {
                0 -> question = Question("1 + 1 = ?", arrayOf("1", "2", "3", "4"), 1)
                1 -> question = Question("1 + 2 = ?", arrayOf("1", "2", "3", "4"), 2)
                else -> question = Question("1 + 3 = ?", arrayOf("1", "2", "3", "4"), 3)
            }
        } else if (message == "Physics") {
            when(count) {
                0 -> question = Question("If an object is in motion, what kind of energy does it apply?",
                        arrayOf("Potential Energy", "Caloric Energy", "Kinetic Energy", "Metabolic Energy"), 2)
                1 -> question = Question("What is the force holds back a sliding object?",
                        arrayOf("Friction", "Gravity", "Momentum", "Deceleration"), 0)
                else -> question = Question("Force is measured by what unit of measure?",
                        arrayOf("Joules", "Degrees", "Newtons", "Calories"), 2)
            }
        } else {
            when(count) {
                0 -> question = Question("Which Super Hero Team Does Johnny Storm Belong To?",
                        arrayOf("Ultimate Avengers", "The Justice League", "The X-Men", "The Fantastic 4"), 3)
                1 -> question = Question("What Is Anthony Stark's Super Hero Name?",
                        arrayOf("Spider Man", "Iron Man", "Deadpool", "Wolverine"), 1)
                else -> question = Question("How old is Thor?",
                        arrayOf("25 years old", "100 years old", "500 years old", "At lease 1000 years old"), 3)
            }
        }

        implement(question, textView, answer1, answer2, answer3, answer4)

        // Toast.makeText(this, numOfCorrect.toString(), Toast.LENGTH_SHORT).show()
        radioGroup.setOnCheckedChangeListener{group, CheckedId ->
            submit.isClickable = true

            var currentAnswer = findViewById<RadioButton>(CheckedId).text
            //Log.i("Quiz", math1.options[math1.answer])
            if(currentAnswer == question.options[question.answer]) {
                numOfCorrect += 1
            }
            submit.setOnClickListener {
                val intent = Intent(this, Answer::class.java)
                // To pass any data to next activity
                intent.putExtra("message", message)
                intent.putExtra("numOfCorrect", numOfCorrect)
                intent.putExtra("question", question)
                intent.putExtra("currentAnswer", currentAnswer)
                intent.putExtra("count", count + 1)
                startActivity(intent)
            }
        }


    }

    private fun implement(question: Question, textView: TextView, answer1: RadioButton,
                          answer2: RadioButton, answer3: RadioButton, answer4: RadioButton): Unit {
        textView.text = question.question
        answer1.text = question.options[0]
        answer2.text = question.options[1]
        answer3.text = question.options[2]
        answer4.text = question.options[3]
    }
}
