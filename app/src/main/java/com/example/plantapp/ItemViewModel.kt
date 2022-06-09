package com.example.plantapp

import android.os.Parcel
import android.os.Parcelable

data class ItemViewModel (val image: Int, val text: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemViewModel> {
        override fun createFromParcel(parcel: Parcel): ItemViewModel {
            return ItemViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ItemViewModel?> {
            return arrayOfNulls(size)
        }
    }
}