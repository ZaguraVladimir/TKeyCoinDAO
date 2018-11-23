package ciphers

import java.io.File

class Dictionaries {

    val EN: Map<String, String> = File("ciphers.getDictionaryEN.txt")
        .readLines()
        .asSequence()
        .filter { it.length > 1 }
        .associate { it.substringBefore('|') to it.substringAfter('|') }

    val RU: Map<String, String> = File("ciphers.getDictionaryRU.txt")
        .readLines()
        .asSequence()
        .filter { it.length > 1 }
        .associate { it to it }
}