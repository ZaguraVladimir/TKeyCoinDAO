import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.nio.ByteBuffer
import javax.imageio.ImageIO

fun main(args: Array<String>) {

    val srcFile = File("stage1\\img.jpg")
    val srcImg = ImageIO.read(srcFile)
    val sizeImg = Size(srcImg.width, srcImg.height)

    // Почистим файлы результатов, если они есть
    File("stage1\\result\\").listFiles().forEach { it.delete() }

    // Создадим массив, в который перенесем пиксели изображения.
    val arrPixelsSrc = Array(sizeImg.height) { _ -> Array(sizeImg.width) { 0 } }
    for (row in arrPixelsSrc.indices) {
        for (column in arrPixelsSrc[row].indices) {
            arrPixelsSrc[row][column] = srcImg.getRGB(column, row)
        }
    }

    // Получим все встречающиеся цвета
    val allColors = arrPixelsSrc.flatMap { it.toList() }.toSortedSet()

    // Извлечем каждый цвет в отдельный файл, каждый цвет преобразуем в черный
    val whiteColor = Color(255, 255, 255, 255)
    val blackColor = Color(0, 0, 0, 255)
    val arrPixelDst = Array(sizeImg.height) { _ -> Array(sizeImg.width) { 0 } }
    allColors.forEach {
        for (row in arrPixelsSrc.indices) {
            for (column in arrPixelsSrc[row].indices) {
                arrPixelDst[row][column] = when (arrPixelsSrc[row][column]) {
                    it -> blackColor.rgb
                    else -> whiteColor.rgb
                }
            }
        }
        val color = Color(it)
        val red = ("0" + color.red.toString(16).toUpperCase()).takeLast(2)
        val green = ("0" + color.green.toString(16).toUpperCase()).takeLast(2)
        val blue = ("0" + color.blue.toString(16).toUpperCase()).takeLast(2)
        val name = "$red$green$blue"
        writeImage(name, arrPixelDst)
    }
    println("Проверяем полученные изображения, в большинстве своём они не содержат данных, но на нескольких из них встречается QR-код, в котором закодирована ссылка на: hrsecuritytkeycoin.pro")
    println("Далее см. Stage 2")

    println("\nP.S. В EXIF картинки найден массив чисел с подсказкой.")
    val messInEXIF = "208 159 208 190 208 184 208 179 209 128 208 176 208 185 209 130 208 181 32 209 129 32 209 143 209 128 208 186 208 190 209 129 209 130 209 140 209 142"
    println(messInEXIF)
    val byteInEXIF = messInEXIF.split(" ").map { it.toInt().toByte() }.toByteArray()
    println("Который содержит строку в кодировке UTF: \"${Charsets.UTF_8.decode(ByteBuffer.wrap(byteInEXIF))}\"")

}

fun writeImage(name: String, arrPixels: Array<Array<Int>>) {

    val bufferedImage = BufferedImage(arrPixels[0].size, arrPixels.size, BufferedImage.TYPE_INT_RGB)
    arrPixels.forEachIndexed { row, columns ->
        columns.forEachIndexed { column, color ->
            bufferedImage.setRGB(column, row, color)
        }
    }
    ImageIO.write(bufferedImage, "jpg", File("stage1\\result\\$name.jpg"))
}
