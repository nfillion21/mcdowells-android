package pgm.poolp.core.data

import pgm.poolp.core.domain.BigMick

interface BigMickDataSource {
    suspend fun getBigMicks(page:Int): List<BigMick>
}
