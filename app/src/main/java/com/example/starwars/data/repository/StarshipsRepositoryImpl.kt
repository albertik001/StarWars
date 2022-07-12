package com.example.starwars.data.repository

import androidx.paging.map
import com.example.starwars.data.remote.apiservices.StarshipsApi
import com.example.starwars.data.remote.dtos.toDomain
import com.example.starwars.data.remote.pagingsource.StarshipsPagingSource
import com.example.starwars.domain.common.Either
import com.example.starwars.domain.models.StarshipsModel
import com.example.starwars.domain.repository.StarshipsRepository
import com.example.starwars.presentation.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StarshipsRepositoryImpl @Inject constructor(
    private val starshipsApi: StarshipsApi
) : BaseRepository(), StarshipsRepository {

    override fun fetchStarships(): Flow<Either<String, StarshipsModel>> = doRequest {
        val page = 0
        starshipsApi.fetchStarships(page).toDomain()
    }

    override fun fetchPagedStarships() =
        doPagingRequest(StarshipsPagingSource(starshipsApi)).map { pagingData ->
            pagingData.map {
                it.toDomain()
            }
        }


}