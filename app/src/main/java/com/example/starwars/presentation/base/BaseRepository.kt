package com.example.starwars.presentation.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingConfig.Companion.MAX_SIZE_UNBOUNDED
import androidx.paging.PagingSource
import com.example.starwars.domain.common.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseRepository {

    protected fun <T> doRequest(
        gatherIfSucceed: ((T) -> Unit)? = null,
        request: suspend () -> T,
    ) = flow<Either<String, T>> {
        request().also { data ->
            gatherIfSucceed?.invoke(data)
            emit(Either.Right(value = data))
        }

    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Left(exception.localizedMessage ?: "An error occurred"))
    }

    protected fun <T> doRequest(
        gatherIfSucceed: ((T) -> Unit)? = null,
        writeToDatabase: suspend (T) -> Unit,
        request: suspend () -> T,
    ) = flow<Either<String, T>> {
        request().also { data ->
            gatherIfSucceed?.invoke(data)
            writeToDatabase(data)
            emit(Either.Right(value = data))
        }

    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Left(exception.localizedMessage ?: "An error occurred"))
    }

    protected fun <Key : Any, Model : Any> doPagingRequest(
        pagingSource: PagingSource<Key, Model>,
    ) =
        Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = true,
                initialLoadSize = 20,
                maxSize = MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE

            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
}