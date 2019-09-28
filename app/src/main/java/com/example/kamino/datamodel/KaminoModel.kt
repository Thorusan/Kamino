package com.example.kamino.datamodel

import org.threeten.bp.LocalDateTime

object KaminoModel {
    data class KaminoPlanet(
        val name: String,
        val rotationPeriod: Int,
        val orbitalPeriod: Int,
        val diameter: Int,
        val climate: String,
        val gravity: String,
        val terrain: String,
        val surface_water: Int,
        val population: Int,
        val residents: Array<Resident>,
        //val created: LocalDateTime,
        val created: String,
        //val edited: LocalDateTime,
        val edited: String,
        val imageUrl: String,
        val likes: Int
    )

    data class Resident(
        val name: String,
        val height: Int,
        val mass: Double,
        val hairColor: String,
        val skinColor: String,
        val eyeColor: String,
        val birthYear: String,
        val gender: String,
        val population: Int,
        val homeworld: String,
        //val created: LocalDateTime,
        val created: String,
        //val edited: LocalDateTime,
        val edited: String,
        val imageUrl: String

    )
}