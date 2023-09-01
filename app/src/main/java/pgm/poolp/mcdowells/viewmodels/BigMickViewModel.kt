package pgm.poolp.mcdowells.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import pgm.poolp.mcdowells.BigMickSource
import pgm.poolp.mcdowells.data.BigMick
import javax.inject.Inject

@HiltViewModel
class BigMickViewModel @Inject constructor(
    private val bigMickSource: BigMickSource
) : ViewModel() {

    val bigMicks: Flow<PagingData<BigMick>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { bigMickSource }
    ).flow.cachedIn(viewModelScope)
}