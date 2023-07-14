import java.awt.Color
import java.awt.Image
import java.awt.image.BufferedImage

import java.io.File

class PictureFactory {
    private fun getBrightness(red: Int, green: Int, blue: Int): Int {
        return (0.299 * red + 0.587 * green + 0.114 * blue).toInt()
    }

    private fun getSymbol(brightness: Int): String {
        return when {
            brightness >= 240 -> "@"
            brightness >= 220 -> "$"
            brightness >= 200 -> "#"
            brightness >= 180 -> "*"
            brightness >= 160 -> "!"
            brightness >= 140 -> "="
            brightness >= 120 -> ";"
            brightness >= 100 -> ":"
            brightness >= 80 -> "~"
            brightness >= 60 -> "-"
            brightness >= 40 -> ","
            brightness >= 20 -> "."
            else -> " "
        }
    }

    fun resize(bufferedImage: BufferedImage, newWidth: Int, newHeight: Int): BufferedImage {
        val temporary = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT)
        val result = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB)

        result.graphics.drawImage(temporary, 0, 0, null)

        return result
    }

    fun convertOld(bufferedImage: BufferedImage): String {
        val result = mutableListOf<String>()

        val height = bufferedImage.height
        val width = bufferedImage.width

        for (y in 0..<height) {
            var row = ""

            for (x in 0..<width) {
                val color = Color(bufferedImage.getRGB(x, y))

                val brightness = getBrightness(color.red, color.green, color.blue)

                row += getSymbol(brightness)
            }

            result.add(row)
        }

        return result.joinToString("\n")
    }

    fun convert(source: BufferedImage, config: Config): String {
        val (width, height, aspectRatio) = config

        val result = mutableListOf<String>()

        val target = resize(source,
            width,
            (height * aspectRatio).toInt()
        )

        for (y in 0..<target.height) {
            var temporary = ""

            for (x in 0..<target.width) {
                val color = Color(target.getRGB(x, y))

                val brightness = getBrightness(
                    color.red,
                    color.green,
                    color.blue
                )

                temporary += getSymbol(brightness)
            }

            result.add(temporary)
        }

        return result.joinToString("\n")
    }
}
