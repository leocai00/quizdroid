package edu.washington.wanxic.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra("keyIdentifier")
        val description = findViewById<TextView>(R.id.description)
        val button= findViewById<Button>(R.id.begin)

        button.setOnClickListener {
            val intent = Intent(this, Quiz::class.java)
            // To pass any data to next activity
            intent.putExtra("keyIdentifier", message)
            // start your next activity
            startActivity(intent)
        }
        describe(message, description)
    }

    private fun describe(message: String, display: TextView): Unit {
        when(message) {
            "Math" -> display.text = getString(R.string.math)
            "Physics" -> display.text = getString(R.string.physics)
            else -> display.text = getString(R.string.heroes)
        }
    }
}
