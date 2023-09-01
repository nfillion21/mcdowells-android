package pgm.poolp.mcdowells

import androidx.annotation.NonNull
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import pgm.poolp.mcdowells.data.BigMick

class BigMickRepository(private val ktorHttpClient: HttpClient) {
    suspend fun getPosts(page: Int):List<BigMick> {

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
        /*
        return listOf(
            BigMick(
                id = 1,
                title = "",
                description = "",
                image = ""
            )
        )
        */
    }
}