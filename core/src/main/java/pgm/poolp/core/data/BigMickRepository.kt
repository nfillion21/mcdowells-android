package pgm.poolp.core.data

import pgm.poolp.core.domain.BigMick

class BigMickRepository (private val mBigMickDataSource: BigMickDataSource) {
    suspend fun getBigMicks(page:Int):List<BigMick> = mBigMickDataSource.getBigMicks(page)
}