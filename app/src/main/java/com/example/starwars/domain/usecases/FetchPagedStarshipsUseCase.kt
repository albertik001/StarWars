package com.example.starwars.domain.usecases

import com.example.starwars.domain.repository.StarshipsRepository
import javax.inject.Inject

class FetchPagedStarshipsUseCase @Inject constructor(
    private val starshipsRepository: StarshipsRepository
) {
    operator fun invoke() = starshipsRepository.fetchPagedStarships()
}