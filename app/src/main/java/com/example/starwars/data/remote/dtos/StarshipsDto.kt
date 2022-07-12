package com.example.starwars.data.remote.dtos


import com.example.starwars.domain.models.StarshipsModel
import com.google.gson.annotations.SerializedName

data class StarshipsDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<Result>
)

fun StarshipsDto.toDomain() = StarshipsModel(count, next, previous, results.map { it.toDomain() })