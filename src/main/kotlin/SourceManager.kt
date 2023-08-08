import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class SourceManager {
    fun getSource(path: String): BufferedImage? {
        val file = File(path)

        if (file.isDirectory || !file.exists()) return null

        return ImageIO.read(file)
    }

    fun getSource(file: File): BufferedImage {
        return ImageIO.read(file)
    }
}
