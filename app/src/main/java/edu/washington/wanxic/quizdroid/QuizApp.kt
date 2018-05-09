package edu.washington.wanxic.quizdroid

import android.app.Application
import android.os.Environment
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.IOException

class QuizApp : Application() {

    companion object {
        private lateinit var instance : QuizApp
        private lateinit var topicList : Array<Topic>
        // private lateinit var topicRepo : TopicRepository
    }

    fun getInstance() : QuizApp? {
        return instance
    }

    fun getTopicList(): Array<Topic>? {
        return topicList
    }

    fun fatchJson(url: String): Array<Topic>? {
        // var url: String = "http://tednewardsandbox.site44.com/questions.json"
        Log.i("MainActivity", "set on fire")
        val request = Request.Builder().url(url).build()
        Log.i("MainActivity", "request on fire")
        val client = OkHttpClient()
        Log.i("MainActivity", "client on fire")
        var topicList: Array<Topic>? = null

        client.newCall(request).enqueue(object: okhttp3.Callback {
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("QuizApp", "onResponse on fire")
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                topicList = gson.fromJson(body, Array<Topic>::class.java)
                Log.i("MainActivity", topicList.toString())
            }

            override fun onFailure(call: Call?, e: IOException?) {
                print("fail to execute request")
                //To change body of created functions use File | Settings | File Templates.
            }
        })
        return topicList
    }

    fun defaultData() {
        val data = Environment.getDataDirectory()
        val file = File(data.absolutePath + "/questions.json")
        val input = file.inputStream().bufferedReader().use{it.readText()}
        val gson = GsonBuilder().create()
        topicList = gson.fromJson(input, Array<Topic>::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "onCreate fired")
        // topicRepo = Info()
        defaultData()
    }

    fun getTopicsName(): ArrayList<String> {
        var topicsText : ArrayList<String> = arrayListOf()
        for (item: Topic in topicList) {
            topicsText.add(item.getTitle())
        }
        return topicsText
    }

    fun getTopic(singleTopic : String) : Topic? {
        for(item : Topic in topicList) {
            if(item.getTitle() == singleTopic) {
                return item
            }
        }
        return null
    }

    fun getQuestions(singleTopic : String) : Array<Question>? {
        for(item : Topic in topicList) {
            if(item.getTitle() == singleTopic) {
                return item.getQuestions()
            }
        }
        return null
    }

    fun getDescription(): ArrayList<String> {
        var description : ArrayList<String> = arrayListOf()
        for (item: Topic in topicList) {
            description.add(item.getDesc())
        }
        return description //To change body of created functions use File | Settings | File Templates.
    }
}
