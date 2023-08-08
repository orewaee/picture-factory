import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val width: Int,
    val height: Int,
    val aspectRatio: Double = 1.0
)
