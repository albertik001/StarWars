package com.example.starwars.data.repository

import androidx.paging.map
import com.example.starwars.data.remote.apiservices.StarshipsApi
import com.example.starwars.data.remote.dtos.toDomain
import com.example.starwars.data.remote.pagingsource.StarshipsPagingSource
import com.example.starwars.domain.common.Either
import com.example.starwars.domain.models.ResultModel
import com.example.starwars.domain.repository.StarshipsRepository
import com.example.starwars.presentation.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StarshipsRepositoryImpl @Inject constructor(
    private val starshipsApi: StarshipsApi
) : BaseRepository(), StarshipsRepository {


    override suspend fun fetchPagedStarships() =
        doPagingRequest(StarshipsPagingSource(starshipsApi)).map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }

    override suspend fun fetchStarship(id: Int): Flow<Either<String, ResultModel>> = doRequest {
        starshipsApi.fetchStarshipDetail(id).toDomain()
    }

}