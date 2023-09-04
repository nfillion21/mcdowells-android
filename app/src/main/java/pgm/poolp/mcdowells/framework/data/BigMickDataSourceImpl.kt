package pgm.poolp.mcdowells.framework.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import pgm.poolp.core.data.BigMickDataSource
import pgm.poolp.core.domain.BigMick
import pgm.poolp.mcdowells.framework.data.entity.BigMickRemote
import javax.inject.Inject

class BigMickDataSourceImpl @Inject constructor(private val ktorHttpClient: HttpClient) :
    BigMickDataSource {

    override suspend fun getBigMicks(page:Int):List<BigMick> {

        val list:List<BigMickRemote> = ktorHttpClient.get(
            "https://techcrunch.com/wp-json/wp/v2/posts?per_page=10&context=embed&page=$page"
        ).body()

        return list.map { bigMick ->
            BigMick(
                id = bigMick.id,
                title = bigMick.title.title,
                description = bigMick.description.description,
                image = bigMick.image
            )
        }
    }
}