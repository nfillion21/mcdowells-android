package pgm.poolp.mcdowells.framework.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BigMickRemote(
    val id: String,
    @SerialName("created_at")
    val createdAt: String
)