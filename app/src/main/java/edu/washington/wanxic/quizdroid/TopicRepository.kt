package edu.washington.wanxic.quizdroid

interface TopicRepository {
    fun getTopics() : ArrayList<Topic>
    fun getTopic(topic : String) : Topic?
    fun getTopicsName(): ArrayList<String>
    fun getQuestions(topic : String) : Collection<Question>?
    fun getShortDescription(): ArrayList<String>
}