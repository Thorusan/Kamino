package com.example.kamino.datamodel

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

object KaminoModel {
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
        val residents: Array<String>,
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
    )

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

    )
}