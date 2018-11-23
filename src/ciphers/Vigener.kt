package ciphers

class Vigener : ICryptography {

    lateinit var alphabet: Alphabet
    lateinit var key: StringKey

    override fun encode(message: String) = proc(message, true)

    override fun decode(message: String) = proc(message, false)

    private fun proc(message: String, encode: Boolean): String {
        key.reset()
        return message.map { getChar(it, encode) }.joinToString("")
    }

    private fun getChar(c: Char, encode: Boolean): Char {
        var symbol = c.toLowerCase()
        if (symbol.isLetter()) {
            val num = alphabet.getNumByChar(symbol)
            val offset = if (encode) alphabet.getNumByChar(key.next) else -alphabet.getNumByChar(key.next)
            symbol = alphabet.getCharByNum(num, offset)
        }
        return if (c.isUpperCase()) symbol.toUpperCase() else symbol.toLowerCase()
    }
}