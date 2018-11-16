import java.math.BigDecimal
import java.math.RoundingMode


// Зашифрованное сообщение на странице hrsecuritytkeycoin.pro
// 28 групп по 5 символов + 1 группа в 3 символа = 143 символа
val strEncrypt = """EIKOX NWSXT DDIAN CDUTH STOEO ZCXHE NIIER RXTSH
                                TADIF CTAXS TLXHI XQIHX CAEKA AENWU EXCKN LESSX
                                XIERT AXDLY HSBIT XXHNM XXDKE LSHIB EXOTW OETOX
                                XDYXB NRWTY XCCAE XAEIA WBS"""
    .replace(" ", "")
    .replace("\n", "")

val arrEncrypt = strEncrypt.toCharArray()

fun main(args: Array<String>) {

    // Так как и текст на картинке приглашения, и строка подсказки из EXIF картинки были на кирилице,
    // то считаем что и зашифрованное сообщение тоже на кирилице.

    println(strEncrypt)
    arrEncrypt.forEach { println("$it\t${it.toByte()}\t") }

    // Поищем повторяющиеся слова
    findWords()

    //val t = arrEncrypt.groupBy { it }.map { it.key to it.value.count() }.associate { it.first to it.second }
    //t.forEach { char, count -> println("$char\t$count") }
//    for (i in 0..arrEncrypt.size-1 step 2) {
//        println("${arrEncrypt[i]}${arrEncrypt[i + 1]}")
//    }

    // Предположение о том что, сообщение является одноалфавитным шифром замены не подтвердилось, частотный анализ выдает абракадабру
    //cipherReplace()

    //dictionaryEN.forEach { println("$it\t${it.toInt()}\t${it.toLowerCase()}\t${it.toLowerCase().toInt()}") }
    //dictionaryRU.forEach { println("$it\t${it.toInt()}\t${it.toLowerCase()}\t${it.toLowerCase().toInt()}") }
}

fun findWords() {

    for (size in 2..10){
        val words = mutableMapOf<String, Int>()

        while (i + size <= strEncrypt.length){

        }
        println(words)
    }
}


fun cipherReplace() {

    // Частотность символов в зашифрованном сообщении
    val frequencySymbols = dictionaryEN
        .associate { char -> char to arrEncrypt.filter { char == it }.count().toDouble() / arrEncrypt.size * 100 }
        .map { it.key to BigDecimal(it.value).setScale(2, RoundingMode.HALF_UP).toDouble() }
        .associate { it.first to it.second }

    // Сопоставим символы по частотности
//    val frequencyNum = frequencySymbols.map { it.value }.sortedByDescending { it }
//    val baseFrequencyNum = baseFrequencySymbols.map { it.value }.sortedByDescending { it }
//    val dictDecrypt = mutableMapOf<Char, Char>()
//    for (index in frequencyNum.indices){
//        val x = 0
//        //var encodeSymbol = baseFrequencySymbols[baseFrequencyNum[index]]
//        var decodeSymbol = frequencySymbols.get frequencyNum[index]
////        dictDecrypt[encodeSymbol] = decodeSymbol
//    }

    val dictDecrypt = mutableMapOf<Char, Char>(
        'E' to 'X',
        'T' to 'E',
        'A' to 'T',
        'O' to 'I',
        'I' to 'A',
        'N' to 'S',
        'S' to 'H',
        'H' to ' ',
        'R' to ' ',
        'D' to ' ',
        'L' to 'O',
        'C' to 'W',
        'U' to ' ',
        'M' to ' ',
        'W' to ' ',
        'F' to ' ',
        'G' to 'Y',
        'Y' to 'U',
        'P' to 'M',
        'B' to ' ',
        'V' to ' ',
        'K' to ' ',
        'X' to ' ',
        'J' to ' ',
        'Q' to ' ',
        'Z' to ' '
    )

    println(arrEncrypt)
    println(arrEncrypt.map { dictDecrypt[it] }.joinToString(""))

    println(baseFrequencySymbolsEN)
    println(frequencySymbols)

    println(baseFrequencySymbolsEN.map { it.value }.sum())
    println(frequencySymbols.map { it.value }.sum())
}