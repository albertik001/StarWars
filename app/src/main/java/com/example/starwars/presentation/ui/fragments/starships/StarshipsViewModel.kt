package com.example.starwars.presentation.ui.fragments.starships

import androidx.paging.PagingData
import com.example.starwars.domain.models.ResultModel
import com.example.starwars.domain.usecases.FetchPagedStarshipsUseCase
import com.example.starwars.presentation.base.BaseViewModel
import com.example.starwars.presentation.models.ResultUI
import com.example.starwars.presentation.models.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StarshipsViewModel @Inject constructor(
    private val fetchPagedStarshipsUseCase: FetchPagedStarshipsUseCase
) :
    BaseViewModel() {


    suspend fun fetchPagedStarships(): Flow<PagingData<ResultUI>> {
        val pagedData = fetchPagedStarshipsUseCase() as Flow<PagingData<ResultModel>>
        return pagedData.gatherPagingRequest { it.toUI() }
    }

}