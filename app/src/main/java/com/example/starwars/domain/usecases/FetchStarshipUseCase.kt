package com.example.starwars.domain.usecases

import com.example.starwars.domain.repository.StarshipsRepository
import javax.inject.Inject

class FetchStarshipUseCase @Inject constructor(private val starshipsRepository: StarshipsRepository) {

    suspend operator fun invoke(id: Int) = starshipsRepository.fetchStarship(id)
}