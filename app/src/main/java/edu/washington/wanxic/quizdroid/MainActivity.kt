package edu.washington.wanxic.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.app.ListActivity
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import android.widget.TextView
import android.widget.AdapterView.OnItemClickListener




class MainActivity : AppCompatActivity() {

    var topics = arrayOf("Math", "Physics", "Marvel Super Heroes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView1 = findViewById<ListView>(R.id.listView1)
        val adapter = ArrayAdapter<String>(this,
                R.layout.list_item_layout, topics)
        listView1.adapter = adapter

        listView1.onItemClickListener= object: AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                val topic = listView1.getItemAtPosition(position).toString()
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
