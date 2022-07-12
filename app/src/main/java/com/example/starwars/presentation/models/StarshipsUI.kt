package com.example.starwars.presentation.models

import com.example.starwars.domain.models.StarshipsModel

data class StarshipsUI(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<ResultUI>
)

fun StarshipsModel.toUI() = StarshipsUI(count, next, previous, results.map { it.toUI() })
