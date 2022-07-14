package com.example.starwars.domain.repository

import com.example.starwars.domain.common.Either
import com.example.starwars.domain.models.ResultModel
import com.example.starwars.domain.typeAliases.NotAnActualPagingData
import kotlinx.coroutines.flow.Flow

interface StarshipsRepository {
    suspend fun fetchPagedStarships(): NotAnActualPagingData
    suspend fun fetchStarship(id: Int): Flow<Either<String, ResultModel>>
}