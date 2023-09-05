package pgm.poolp.mcdowells.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pgm.poolp.core.domain.BigMick
import pgm.poolp.core.usecase.GetBigMicksUseCase

class BigMickPagingSource(
    private val getBigMicksUseCase: GetBigMicksUseCase
) : PagingSource<Int, BigMick>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BigMick> {
        return try {
            val position = params.key ?: 1
            val bigMicks = getBigMicksUseCase.invoke(position)
            val totalPages = bigMicks.takeIf { it.isNotEmpty() }?.get(0)?.totalPages

            LoadResult.Page(
                data = bigMicks,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (totalPages != null && position >= totalPages) null else position.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BigMick>): Int? {
        return state.anchorPosition
    }
}