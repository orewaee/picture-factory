import java.io.File

import kotlin.system.exitProcess

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun main(arguments: Array<String>) {
    if (arguments.size != 2) {
        println("Invalid arguments specified")
        exitProcess(1)
    }

    if (arguments[1] == "settings") {
        println("The file with the result cannot be called that")
        exitProcess(1)
    }

    var settings = Settings(512, 512)

    val settingsFile = File("settings.json")
    if (!settingsFile.exists()) println("Advanced settings not found so default settings are used")
    else settings = Json.decodeFromString<Settings>(settingsFile.readText())

    val (width, height, aspectRatio) = settings

    println("""
        
        Settings:
        width: $width
        height: $height
        aspectRatio: $aspectRatio
        
    """.trimIndent())

    val pictureFactory = PictureFactory()
    val sourceManager = SourceManager()

    val folder = File(arguments[0])

    if (!folder.exists() || !folder.isDirectory) {
        println("Wrong folder path was specified")
        exitProcess(1)
    }

    val files = folder.listFiles()

    if (files == null || files.isEmpty()) {
        println("An error occurred while getting files from a folder")
        exitProcess(1)
    }

    val frames = mutableListOf<String>()

    for (file in files) {
        val name = file.name
        val extension = file.extension

        println("Processing the $name file...")

        if (extension !in arrayOf("png", "jpg")) {
            println("Skipped")
            continue
        }

        val source = sourceManager.getSource(file)

        frames.add(pictureFactory.convert(source, settings))
    }

    val result = File(arguments[1] + ".json")
    if (!result.exists()) result.createNewFile()

    result.writeText(Json.encodeToString(frames))

    println("\nComplete!")
}