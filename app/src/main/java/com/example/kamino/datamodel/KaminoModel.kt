package com.example.kamino.datamodel

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class KaminoModel {
    data class KaminoPlanet(
        @SerializedName("name")
        val name: String,
        @SerializedName("rotation_period")
        val rotationPeriod: Int,
        @SerializedName("orbital_period")
        val orbitalPeriod: Int,
        @SerializedName("diameter")
        val diameter: Int,
        @SerializedName("climate")
        val climate: String,
        @SerializedName("gravity")
        val gravity: String,
        @SerializedName("terrain")
        val terrain: String,
        @SerializedName("surface_water")
        val surfaceWater: Int,
        @SerializedName("population")
        val population: Int,
        @SerializedName("residents")
        val residents: ArrayList<String>,
        //val created: LocalDateTime,
        @SerializedName("created")
        val created: String,
        //val edited: LocalDateTime,
        @SerializedName("edited")
        val edited: String,
        @SerializedName("image_url")
        val imageUrl: String,
        @SerializedName("likes")
        val likes: Int


    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(rotationPeriod)
            parcel.writeInt(orbitalPeriod)
            parcel.writeInt(diameter)
            parcel.writeString(climate)
            parcel.writeString(gravity)
            parcel.writeString(terrain)
            parcel.writeInt(surfaceWater)
            parcel.writeInt(population)
            parcel.writeList(residents)
            parcel.writeString(created)
            parcel.writeString(edited)
            parcel.writeString(imageUrl)
            parcel.writeInt(likes)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<KaminoPlanet> {
            override fun createFromParcel(parcel: Parcel): KaminoPlanet {
                return KaminoPlanet(parcel)
            }

            override fun newArray(size: Int): Array<KaminoPlanet?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Resident(
        @SerializedName("name")
        val name: String,
        @SerializedName("height")
        val height: Int,
        @SerializedName("mass: Double")
        val mass: Double,
        @SerializedName("hair_color")
        val hairColor: String,
        @SerializedName("skin_color")
        val skinColor: String,
        @SerializedName("eye_color")
        val eyeColor: String,
        @SerializedName("birth_<ear")
        val birthYear: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("population")
        val population: Int,
        @SerializedName("coord")
        val homeworld: String,
        //val created: LocalDateTime,
        @SerializedName("created:")
        val created: String,
        //val edited: LocalDateTime,
        @SerializedName("edited")
        val edited: String,
        @SerializedName("image_url")
        val imageUrl: String



    ) : Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name)
            parcel.writeInt(height)
            parcel.writeDouble(mass)
            parcel.writeString(hairColor)
            parcel.writeString(skinColor)
            parcel.writeString(eyeColor)
            parcel.writeString(birthYear)
            parcel.writeString(gender)
            parcel.writeInt(population)
            parcel.writeString(homeworld)
            parcel.writeString(created)
            parcel.writeString(edited)
            parcel.writeString(imageUrl)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Resident> {
            override fun createFromParcel(parcel: Parcel): Resident {
                return Resident(parcel)
            }

            override fun newArray(size: Int): Array<Resident?> {
                return arrayOfNulls(size)
            }
        }
    }
}