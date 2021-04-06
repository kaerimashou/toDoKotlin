package com.example.todo.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="text")
    val text: String?,
    @ColumnInfo(name="time")
    val timestamp: Long,
    @ColumnInfo(name="isDone")
    val isDone:Boolean):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun hashCode(): Int {
        return super.hashCode()
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