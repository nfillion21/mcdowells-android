package pgm.poolp.mcdowells

import androidx.annotation.NonNull
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import pgm.poolp.mcdowells.data.BigMick

class BigMickRepository(private val ktorHttpClient: HttpClient) {
    fun getPosts(page: Int):List<BigMick> {
        /*
        val httpResponse:HttpResponse = ktorHttpClient.get(
            "https://techcrunch.com/wp-json/wp/v2/posts?per_page=10&context=embed&page=$page"
        )
        val httpStatus = httpResponse.status
        val httpStatus2 = httpResponse.status
         */

        return listOf(
            BigMick(
                id = 1,
                title = "",
                description = "",
                image = ""
            )
        )
            /*.map { bigMick ->
                BigMick(
                    id = bigMick.id,
                    title = bigMick.title.title,
                    description = bigMick.description.description,
                    image = bigMick.image
                )
             */
    }

}