package edu.washington.wanxic.quizdroid

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)

        val quizApp = application as QuizApp

        setSupportActionBar(findViewById(R.id.my_toolbar))

        val topics: ArrayList<String>? = quizApp.getTopicsName()
        val description: ArrayList<String>? = quizApp.getDescription()

        Log.i("MainActivity", topics.toString())
        // val info :Info = Info()
        Log.i("MainActivity", description.toString())
        val listView1 = findViewById<ListView>(R.id.listView1)
        //val adapter = ArrayAdapter<String>(this,
        //        R.layout.list_item_layout, topics)
        listView1.adapter = CustomAdapter(this, topics!!, description!!)

        listView1.onItemClickListener= object: AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                val topic = listView1.getItemAtPosition(position).toString()
                Log.i("MainActivity", topic)
                turn(view, topic)
            }
        }
    }

    fun turn(view: View, topic: String) {
        val intent = Intent(this, SecondActivity::class.java)
        // To pass any data to next activity
        intent.putExtra("keyIdentifier", topic)
        // start your next activity
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, Preferences::class.java)
            startActivity(intent)
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

}

