package pgm.poolp.mcdowells.framework.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pgm.poolp.core.data.BigMickDataSource
import pgm.poolp.core.domain.BigMick
import pgm.poolp.mcdowells.framework.data.entity.BigMicksRemote
import javax.inject.Inject

class BigMickDataSourceImpl @Inject constructor(private val ktorHttpClient: HttpClient) :
    BigMickDataSource {

    override suspend fun getBigMicks(page:Int):List<BigMick> {

        val bigMicksRemote: BigMicksRemote = ktorHttpClient.get(
            ApiRoutes.BIG_MICKS + page
        ).body()

        return bigMicksRemote.results.mapIndexed {i, bigMick ->
            BigMick(
                id = bigMick.id,
                index = (page-1)*10+i+1,
                total = bigMicksRemote.total,
                totalPages = bigMicksRemote.totalPages,
                description = bigMick.description,
                small = bigMick.urls.small,
                regular = bigMick.urls.regular,
                likes = bigMick.likes
            )
        }
    }
}