package pgm.poolp.core.domain

data class BigMick(
    val id: String,
    val index: Int,
    val total: Int,
    val totalPages: Int,
    val description: String?,
    val likes: Int,
    val small: String,
    val regular: String
)