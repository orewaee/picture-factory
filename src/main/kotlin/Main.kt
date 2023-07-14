import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

import java.io.File

fun main() {
    val pictureFactory = PictureFactory()
    val sourceManager = SourceManager()

    val frames = mutableListOf<String>()

    val source = sourceManager.getSource("orewaee.png") ?: return println("error while getting source")

    val config = Config(
        (source.width * 1.0).toInt(),
        (source.height * 1.0).toInt(),
        15.0 / 48.0
    )

    frames.add(
        pictureFactory.convert(
            source,
            config
        )
    )

    val result = File("static.json")

    if (!result.exists()) result.createNewFile()

    result.writeText(Json.encodeToString(frames))
}