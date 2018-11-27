package TheCodeBook

import ciphers.Alphabets
import ciphers.CaesarGronsfeld
import ciphers.Message
import ciphers.NumKey

/*
Шифр Цезаря

Зашифрованная строка
"MHILY LZA ZBHL XBPZXBL MVYABUHL HWWPBZ JSHBKPBZ JHLJBZ KPJABT HYJHUBT LZA ULBAYVU"

Расшифрованная строка
FABER EST SUAE QUISQUE FORTUNAE APPIUS CLAUDIUS CAECUS DICTUM ARCANUM EST NEUTRON

Перевод:
Faber est suae quisque fortunae.(Appius Claudius Caecus)

каждый кузнец своей собственной фортуны
*/

fun main(args: Array<String>) {

    val encryptMessage = Message("MHILY LZA ZBHL XBPZXBL MVYABUHL HWWPBZ JSHBKPBZ JHLJBZ KPJABT HYJHUBT LZA ULBAYVU")
    var decryptMessage = Message("")
    println("Message: $encryptMessage")

    val caesar = CaesarGronsfeld()
    caesar.alphabet = Alphabets.EN

    // Подберем сдвиг
    for (shiftCount in 1..Alphabets.EN.symbols.lastIndex) {
        caesar.key = NumKey(shiftCount)
        decryptMessage = caesar.decode(encryptMessage)
        println("Сдвиг: ${caesar.key}, message: ${decryptMessage}")
    }

    // Сдвиг подобран. 7
    println()
    println("-------------------------RESULT----------------------------------")
    caesar.key = NumKey(7)
    decryptMessage = caesar.decode(encryptMessage)
    println("Сдвиг: ${caesar.key}, message: ${decryptMessage}")
}