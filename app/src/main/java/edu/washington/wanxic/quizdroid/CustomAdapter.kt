package edu.washington.wanxic.quizdroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomAdapter(context: Context, items: ArrayList<String>, subItem: ArrayList<String>) : BaseAdapter() {
    var thisItems: ArrayList<String>? = null
    var thisSubItem: ArrayList<String>? = null
    private val myContext: Context

    init {
        myContext = context
        thisItems = items
        thisSubItem = subItem
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowLayoutInflater = LayoutInflater.from(myContext)
        val rowLayout = rowLayoutInflater.inflate(R.layout.row_layout, parent, false)
        val topicText = rowLayout.findViewById<TextView>(R.id.topic)
        val description = rowLayout.findViewById<TextView>(R.id.shortDescription)
        val imageDisplay = rowLayout.findViewById<ImageView>(R.id.imageView)
        topicText.text = thisItems!![position]
        description.text = thisSubItem!![position]
        return rowLayout
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        return thisItems!![position] //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        return position.toLong() //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return thisItems!!.size //To change body of created functions use File | Settings | File Templates.
    }
}