package edu.washington.wanxic.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {

    companion object {
        private lateinit var instance : QuizApp
        private lateinit var topicRepo : TopicRepository
    }


    fun getTopicRepo(): TopicRepository? {
        return topicRepo
    }


    fun getInstance() : QuizApp? {
        return instance
    }


    override fun onCreate() {
        super.onCreate()
        Log.i("QuizApp", "onCreate fired")
        topicRepo = Info()
    }
}
