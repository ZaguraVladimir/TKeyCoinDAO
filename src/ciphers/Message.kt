package ciphers

import java.util.*

class Message(val text: String, delimiter: String = "") {
    val length = text.length
    val words = text.split (delimiter).toSortedSet()
    val chars = text.toCharArray()
    val bytes = text.toByteArray()
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Message: $text")
        sb.appendln(" (length = $length)")
//        sb.appendln("words = $words")
//        sb.appendln("chars = ${Arrays.toString(chars)}")
//        sb.appendln("bytes=${Arrays.toString(bytes)}")
        return sb.toString()
    }
}

inline fun <R> Message.map(transform: (Char) -> R): List<R> {
    return text.mapTo(ArrayList<R>(length), transform)
}