package com.example.starwars.presentation.ui.fragments.starships.detail

import com.example.starwars.domain.usecases.FetchStarshipUseCase
import com.example.starwars.presentation.base.BaseViewModel
import com.example.starwars.presentation.models.ResultUI
import com.example.starwars.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StarshipViewModel @Inject constructor(
    private val starshipUseCase: FetchStarshipUseCase
) : BaseViewModel() {

    private val _starshipsState = mutableUiStateFlow<ResultUI>()
    val starshipsState = _starshipsState.asStateFlow()

    suspend fun fetchStarship(id: Int) = starshipUseCase(id).gatherRequest(_starshipsState) {
        it.toUI()
    }

}