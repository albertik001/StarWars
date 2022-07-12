package com.example.starwars.data.remote.pagingsource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwars.data.remote.apiservices.StarshipsApi
import com.example.starwars.data.remote.dtos.Result
import okio.IOException
import retrofit2.HttpException

class StarshipsPagingSource(private val starshipsApi: StarshipsApi) :
    PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key ?: 1
        return try {
            val response = starshipsApi.fetchStarships(page)

            val nextPageNumber = if (response.next == null) {
                null
            } else
                Uri.parse(response.next).getQueryParameter("page")?.toInt()
            LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)


        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }

}