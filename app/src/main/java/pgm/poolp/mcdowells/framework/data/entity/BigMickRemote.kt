package pgm.poolp.mcdowells.framework.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BigMickRemote(
    val id: Int,
    val title: Title,
    @SerialName("excerpt")
    val description: Description,
    val link: String,
    @SerialName("jetpack_featured_media_url")
    val image: String
)

@Serializable
data class Title(
    @SerialName("rendered")
    val title: String
)

@Serializable
data class Description(
    @SerialName("rendered")
    val description: String
)