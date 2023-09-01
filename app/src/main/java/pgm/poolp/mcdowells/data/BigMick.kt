package pgm.poolp.mcdowells.data

import androidx.annotation.NonNull
import kotlinx.serialization.Serializable

@Serializable
data class BigMick(
    @NonNull val id: Int,
    @NonNull val title: String,
    @NonNull val description: String,
    @NonNull val image: String
)