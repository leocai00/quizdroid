package edu.washington.wanxic.quizdroid

import android.app.Application
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quizApp = application as QuizApp

        val topics: ArrayList<String>? = quizApp.getTopicRepo()?.getTopicsName()
        val description: ArrayList<String>? = quizApp.getTopicRepo()?.getShortDescription()
        val image : ArrayList<Int> = arrayListOf(android.R.drawable.ic_menu_edit,
                android.R.drawable.ic_menu_preferences,
                android.R.drawable.btn_star_big_off)

        Log.i("MainActivity", topics.toString())
        // val info :Info = Info()
        Log.i("MainActivity", description.toString())
        val listView1 = findViewById<ListView>(R.id.listView1)
        //val adapter = ArrayAdapter<String>(this,
        //        R.layout.list_item_layout, topics)
        listView1.adapter = CustomAdapter(this, topics!!, description!!, image)

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
}

