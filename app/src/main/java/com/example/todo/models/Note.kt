package com.example.todo.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name="text")
    var text: String?,
    @ColumnInfo(name="time")
    var timestamp: Long,
    @ColumnInfo(name="isDone")
    var isDone:Boolean,
):Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte()) {
    }

    constructor():this(0,"Pizdec",System.currentTimeMillis(),true){

    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (text != other.text) return false
        if (timestamp != other.timestamp) return false
        if (isDone != other.isDone) return false

        return true
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(text)
        parcel.writeLong(timestamp)
        parcel.writeByte(if (isDone) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }

}