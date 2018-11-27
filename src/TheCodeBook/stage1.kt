package TheCodeBook

import ciphers.*
import java.io.File

fun main(args: Array<String>) {

    val encryptMessage =
        Message(File("TheCodeBook/Задача 1 Простой одноалфавитный шифр замены.txt").readText(), delimiter = " ")
    var decryptMessage = ""
    println(encryptMessage)

    experiments(encryptMessage)
    //showResult(encryptMessage)
}

fun experiments(encryptMessage: Message) {

    val alphabet = Alphabet(Alphabets.EN)
    alphabet.calcFrequency(File("The Tragedy of Birlstone.txt"))

    val messFreq = getFrequencySymbols(encryptMessage.text, Alphabets.EN)

    var tableReplacements = getTableReplacements(messFreq, alphabet.frequency)

    println(tableReplacements)
    println(encryptMessage.text.map { tableReplacements[it] ?: it }.joinToString(""))
    println()

    tableReplacements = getTableReplacements(messFreq, Alphabets.EN.frequency)

    println(tableReplacements)
    println(encryptMessage.text.map { tableReplacements[it] ?: it }.joinToString(""))
    println()
}

fun showResult(encryptMessage: Message) {

    val tableReplacements = mapOf(
        'X' to 'E',
        'J' to 'T',
        'M' to 'A',
        'P' to 'O',
        'T' to 'I',
        'C' to 'N',
        'R' to 'S',
        'B' to 'H',
        'V' to 'R',
        'N' to 'D',
        'G' to 'L',
        'W' to 'C',
        'A' to 'U',
        'Y' to 'M',
        'I' to 'W',
        'H' to 'F',
        'L' to 'G',
        'Q' to 'Y',
        'U' to 'P',
        'F' to 'B',
        'D' to 'V',
        'E' to 'K',
        'S' to 'X',
        'K' to 'J',
        'O' to 'Q',
        'Z' to 'Z'
    )
    val decryptMessage = encryptMessage.map { tableReplacements[it] ?: it }.joinToString("")
    println(decryptMessage)
}