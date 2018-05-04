package edu.washington.wanxic.quizdroid

import android.os.Parcel
import android.os.Parcelable

class Topic(val topic : String, val shortDesciptoin : String, val description : String, val questions : Collection<Question>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("questions")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(topic)
        parcel.writeString(shortDesciptoin)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Topic> {
        override fun createFromParcel(parcel: Parcel): Topic {
            return Topic(parcel)
        }

        override fun newArray(size: Int): Array<Topic?> {
            return arrayOfNulls(size)
        }
    }

}