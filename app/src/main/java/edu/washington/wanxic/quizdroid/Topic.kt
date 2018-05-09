package edu.washington.wanxic.quizdroid

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Topic(title: String, desc: String, questions: Array<Question>): Serializable {
    private val title: String = title
    private val desc: String = desc
    private val questions: Array<Question> = questions

    fun getTitle(): String {
        return title
    }

    fun getDesc(): String {
        return desc
    }

    fun getQuestions(): Array<Question> {
        return questions
    }
}
