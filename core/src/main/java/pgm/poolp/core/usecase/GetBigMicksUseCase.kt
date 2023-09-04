package pgm.poolp.core.usecase

import pgm.poolp.core.data.BigMickRepository
import pgm.poolp.core.domain.BigMick

class GetBigMicksUseCase(private val mBigMickRepository: BigMickRepository) {
    suspend fun invoke(page:Int): List<BigMick> = mBigMickRepository.getBigMicks(page)
}