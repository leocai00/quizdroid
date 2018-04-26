package edu.washington.wanxic.quizdroid

import android.os.Parcel
import android.os.Parcelable

class Question(val question: String, val options: Array<String>, val answer: Int): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArray(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(question)
        parcel.writeStringArray(options)
        parcel.writeInt(answer)
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