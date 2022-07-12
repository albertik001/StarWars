package com.example.starwars.domain.models

data class StarshipsModel(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<ResultModel>
)
