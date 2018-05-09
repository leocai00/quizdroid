package edu.washington.wanxic.quizdroid

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Question(text : String, answer : String, answers : Array<String>): Serializable {
    private val text: String = text
    private val answer: String = answer
    private val answers: Array<String> = answers

    fun getText(): String {
        return text
    }

    fun getAnswer(): String {
        return answer
    }

    fun getAnswers(): Array<String> {
        return answers
    }
}