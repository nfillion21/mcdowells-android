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
            val nextPage = params.key ?: 1
            val posts = getBigMicksUseCase.invoke(nextPage)

            LoadResult.Page(
                data = posts,
                prevKey = if (nextPage == 1) null else nextPage.minus(1),
                nextKey = if (posts.isEmpty()) null else nextPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BigMick>): Int? {
        return state.anchorPosition
    }
}