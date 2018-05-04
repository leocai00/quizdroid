package edu.washington.wanxic.quizdroid

import android.os.Parcel
import android.os.Parcelable

class Question(val question : String, val answers : Array<String>, val correctAns : Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArray(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeStringArray(answers)
        parcel.writeInt(correctAns)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }

}