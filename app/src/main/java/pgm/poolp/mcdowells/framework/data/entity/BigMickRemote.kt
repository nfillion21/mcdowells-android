package pgm.poolp.mcdowells.framework.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BigMicksRemote(
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<BigMickRemote>
)

@Serializable
data class BigMickRemote(
    val id: String,
    val description: String?,
    val likes: Int,
    val urls: BigMickRemotePhotos
)

@Serializable
data class BigMickRemotePhotos(
    val thumb: String,
    //val regular: String,
    val small: String
)