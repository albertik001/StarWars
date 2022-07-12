package com.example.starwars.domain.repository

import com.example.starwars.domain.common.Either
import com.example.starwars.domain.models.StarshipsModel
import com.example.starwars.domain.typeAliases.NotAnActualPagingData
import kotlinx.coroutines.flow.Flow

interface StarshipsRepository {
    fun fetchStarships(): Flow<Either<String, StarshipsModel>>
    fun fetchPagedStarships(): NotAnActualPagingData
}