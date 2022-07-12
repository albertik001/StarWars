package com.example.starwars.presentation.models

import com.example.starwars.domain.models.ResultModel
import com.example.starwars.presentation.base.BaseDiffModel

data class ResultUI(
    val cargoCapacity: String,
    val consumables: String,
    val costInCredits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdriveRating: String,
    val length: String,
    val mGLT: String,
    val manufacturer: String,
    val maxAtmospheringSpeed: String,
    val model: String,
    override val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starshipClass: String,
    val url: String
) : BaseDiffModel<String>

fun ResultModel.toUI() = ResultUI(
    cargoCapacity,
    consumables,
    costInCredits,
    created,
    crew,
    edited,
    films,
    hyperdriveRating,
    length,
    mGLT,
    manufacturer,
    maxAtmospheringSpeed,
    model,
    name,
    passengers,
    pilots,
    starshipClass,
    url
)