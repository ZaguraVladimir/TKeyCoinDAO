package ciphers

import java.math.BigDecimal
import java.math.RoundingMode


// Словарь
class Alphabet(
    val name: String = "UNDEFINED",
    symbols: String = "",
    ignoreChars: String = "",
    frequency: Map<Char, Double> = emptyMap()
) {
    val symbols: CharArray = symbols.filterNot { ignoreChars.toUpperCase().contains(it) }.toCharArray()
    private val ignoreChars: CharArray = ignoreChars.toCharArray()
    val frequency = frequency.filterNot { ignoreChars.toUpperCase().contains(it.key) }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendln("Алфавит: $name")
        sb.appendln("Символы: ${symbols.joinToString(", ") { "$it = ${getNumByChar(it)}" }}")
        if (ignoreChars.isNotEmpty()) sb.appendln("Игнорируемые символы: ${ignoreChars.joinToString(", ")}")

        val strFrequency = if (frequency.isNotEmpty()) {
            frequency
                .map { it.value to it.key }
                .groupBy { it.first }
                .map {
                    "${it.key}=[${it.value.map { it.second }.joinToString(", ")}]"
                }
                .joinToString(", ")
        } else "<Не указана>"
        sb.append("Частота символов: $strFrequency")
        return sb.toString()
    }

}

object Alphabets {

    public val EN: Alphabet = Alphabet(
        name = "EN",
        symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
        ignoreChars = "",
        frequency = mapOf(
            'E' to 12.7,
            'T' to 9.06,
            'A' to 8.17,
            'O' to 7.51,
            'I' to 6.97,
            'N' to 6.75,
            'S' to 6.33,
            'H' to 6.09,
            'R' to 5.99,
            'D' to 4.25,
            'L' to 4.03,
            'C' to 2.78,
            'U' to 2.76,
            'M' to 2.41,
            'W' to 2.36,
            'F' to 2.23,
            'G' to 2.02,
            'Y' to 1.97,
            'P' to 1.93,
            'B' to 1.49,
            'V' to 0.98,
            'K' to 0.77,
            'X' to 0.15,
            'J' to 0.15,
            'Q' to 0.10,
            'Z' to 0.05
        )
    )

    public val RU: Alphabet = Alphabet(
        name = "RU",
        symbols = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ",
        ignoreChars = "",
        frequency = mapOf(
            'О' to 10.97,
            'Е' to 8.45,
            'А' to 8.01,
            'И' to 7.35,
            'Н' to 6.70,
            'Т' to 6.26,
            'С' to 5.47,
            'Р' to 4.73,
            'В' to 4.54,
            'Л' to 4.40,
            'К' to 3.49,
            'М' to 3.21,
            'Д' to 2.98,
            'П' to 2.81,
            'У' to 2.62,
            'Я' to 2.01,
            'Ы' to 1.90,
            'Ь' to 1.74,
            'Г' to 1.70,
            'З' to 1.65,
            'Б' to 1.59,
            'Ч' to 1.44,
            'Й' to 1.21,
            'Х' to 0.97,
            'Ж' to 0.94,
            'Ш' to 0.73,
            'Ю' to 0.64,
            'Ц' to 0.48,
            'Щ' to 0.36,
            'Э' to 0.32,
            'Ф' to 0.26,
            'Ъ' to 0.04,
            'Ё' to 0.04
        )
    )
}

// ---------------------------------------------------------------------------------------------------------------------

fun Alphabet.stringToNums(message: String): IntArray = message.map { getNumByChar(it) }.toIntArray()

fun Alphabet.numsToString(nums: IntArray): String = nums.map { getCharByNum(it) }.joinToString("")

fun Alphabet.getCharByNum(num: Int, offset: Int = 0): Char {

    val offset = offset % this.symbols.size
    var num = num + offset - 1

    num = when {
        num >= symbols.size -> num % this.symbols.size
        num < 0 -> symbols.size + num
        else -> num
    }

    return symbols[num]
}

fun Alphabet.getNumByChar(char: Char): Int {

    val num = this.symbols.indexOf(char.toUpperCase()) + 1
    if (num == 0) throw Exception("No symbol \'$char\' in alphabet: ${this.symbols.joinToString(", ")}")

    return num
}


fun String.getFrequencySymbols(alphabet: Alphabet): Map<Char, Double> {

    val str = this.toUpperCase().filter { alphabet.symbols.contains(it) }
    val frequency = alphabet.symbols
        .associate { char -> char to str.filter { char == it }.count().toDouble() / str.length * 100 }
        .map { it.key to BigDecimal(it.value).setScale(2, RoundingMode.HALF_UP).toDouble() }
        .sortedByDescending { it.second }
        .associate { it.first to it.second }
    return frequency
}

fun getTableReplacements(freg: Map<Char, Double>, fregBase: Map<Char, Double>): Map<Char, Char> {

    val freg = freg.map { it.key to it.value }.sortedByDescending { it.second }
    val fregBase = fregBase.map { it.key to it.value }.sortedByDescending { it.second }

    val tableReplacements = mutableMapOf<Char, Char>()
    freg.forEachIndexed { index, pair -> tableReplacements[pair.first] = fregBase[index].first }//

    return tableReplacements
}