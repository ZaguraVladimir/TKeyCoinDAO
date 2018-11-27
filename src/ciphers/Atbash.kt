package ciphers

class Atbash : ICryptography {

    lateinit var alphabet: Alphabet

    override fun encode(message: Message) = proc(message)

    override fun decode(message: Message) = proc(message)

    private fun proc(message: Message) = Message(message.map { getChar(it) }.joinToString(""))

    private fun getChar(c: Char): Char {
        return if (c.isLetter()) {
            alphabet.getCharByNum(alphabet.symbols.size - alphabet.getNumByChar(c) + 1)
        } else {
            c
        }
    }
}