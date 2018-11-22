
import ciphers.*
import java.nio.charset.Charset
import java.util.*


// Зашифрованное сообщение на странице hrsecuritytkeycoin.pro
// 28 групп по 5 символов + 1 группа в 3 символа = 143 символа
// В исходном коде страницы есть подсказка:
// <!-- meta name="text" content="143" -->
// Это скорее всего ключ.
// В кодировке "windows-1251" это символ: "Џ"
// В кодировке "866" это символ: "П"

val strEncrypt = """EIKOX NWSXT DDIAN CDUTH STOEO ZCXHE NIIER RXTSH
                            TADIF CTAXS TLXHI XQIHX CAEKA AENWU EXCKN LESSX
                            XIERT AXDLY HSBIT XXHNM XXDKE LSHIB EXOTW OETOX
                            XDYXB NRWTY XCCAE XAEIA WBS"""
    .replace(" ", "")
    .replace("\n", "")

val arrEncrypt = strEncrypt.toCharArray()
val arrEncryptByte = strEncrypt.toByteArray()

val alphabetRU = AlphabetRU()

var strDecrypt = ""
fun main(args: Array<String>) {

    println("Encrypted message")
    println("String:\t $strEncrypt")
    println("Bytes:\t ${Arrays.toString(strEncrypt.toByteArray())}")
    println()

    if (true) useRSA(strDecrypt)

    return

    arrEncryptByte.forEach { print(it.toString(16)) }

    // Сразу воспользуемся ключем
    // Если применить XOR, то получаем стоку, в которой только кирилица, но она тоже зашифрована, от неё и будем плясать

//    println("Decrypted message")
//    println("String:\t $strDecrypt")
//    println("Bytes:\t ${Arrays.toString(strDecrypt.toByteArray())}")
//    println()

    val arrDecryptByte = strEncrypt.map { it.toInt() xor 143 }.map { it.toByte() }.toByteArray()
    applyEncoding(arrDecryptByte, Charsets.UTF_8)
    applyEncoding(arrDecryptByte, Charsets.UTF_16)
    applyEncoding(arrDecryptByte, Charsets.UTF_16BE)
    applyEncoding(arrDecryptByte, Charsets.UTF_16LE)
    applyEncoding(arrDecryptByte, Charsets.UTF_32)
    applyEncoding(arrDecryptByte, Charsets.UTF_32BE)
    applyEncoding(arrDecryptByte, Charsets.UTF_32LE)
    applyEncoding(arrDecryptByte, Charsets.US_ASCII)
    applyEncoding(arrDecryptByte, Charsets.ISO_8859_1)
    applyEncoding(arrDecryptByte, charset("windows-1251"))
    applyEncoding(arrDecryptByte, charset("866"))

    println()

//    println("Encrypted byte message:\t\t ${strEncrypt.map { it.toInt() }}")
//    println("Decrypted byte message:\t\t ${strDecrypt.map { it.toInt() }}")
//    println()
//

    // Атбаш не подошел
    if (false) useAtbash(strDecrypt)

    // Поиграемся со сдвигом по алфавиту, т.н. шифром Цезаря
    if (false) useCaesar(strDecrypt)
    // Цезарь не подошел


    // Поиграемся со сдвигом по алфавиту, т.н. шифром Гронсфельда, в котором ключ состоит не из одной цифры. Это модификация шифра Цезаря
    if (false) useGronsfeld(strDecrypt)
    // Гронсфельд не подошел

    // Поиграемся с шифром Виженера
    if (false) useVigener(strDecrypt)
    // Виженер не подошел, нет ключа

    // Сдвиг ничего не дал, теперь дело за частотным анализом
    if (false) useFrequency(strDecrypt)


//    val tb = strEncrypt.map { it.toByte() }
//    val t = strEncrypt.map { it.toByte() + 143 }
//    println(tb)


//    strDecrypt.getFrequencySymbols(alphabetRU).forEach { println("${it.key}\t${it.value}") }


//    val arrDecrypt = arrEncrypt.map { it.toInt() + 143 }.map { it.toChar() }.toCharArray()
//    println("arrDecrypt: ${arrDecrypt.map { it.toInt() }}")
//
//    val arrDecryptInt = arrDecrypt.map { it.toInt()}
//    println("arrDecryptInt: $arrDecryptInt")
//
//    println("Sorted: ${arrDecryptInt.sortedBy { it }}")
//    println("Sorted set: ${arrDecryptInt.toSortedSet()}")
//
//    val strDecrypt = String(arrDecrypt.map { it.toByte() }.toByteArray(), charset("windows-1251"))
//    println("strDecrypt: $strDecrypt")


    // Так как и текст на картинке приглашения, и строка подсказки из EXIF картинки были на кирилице,
    // то считаем что и зашифрованное сообщение тоже на кирилице.

    //println(strEncrypt)
//    arrEncrypt.forEach { println("$it\t${it.toByte()}\t") }

    // Поищем повторяющиеся слова
    //findWords()

    //val t = arrEncrypt.groupBy { it }.map { it.key to it.value.count() }.associate { it.first to it.second }
    //t.forEach { char, count -> println("$char\t$count") }
//    for (i in 0..arrEncrypt.size-1 step 2) {
//        println("${arrEncrypt[i]}${arrEncrypt[i + 1]}")
//    }

    // Предположение о том что, сообщение является одноалфавитным шифром замены не подтвердилось, частотный анализ выдает абракадабру
    //cipherReplace()

    //ciphers.getDictionaryEN.forEach { println("$it\t${it.toInt()}\t${it.toLowerCase()}\t${it.toLowerCase().toInt()}") }
    //ciphers.getDictionaryRU.forEach { println("$it\t${it.toInt()}\t${it.toLowerCase()}\t${it.toLowerCase().toInt()}") }
}

fun useAtbash(encryptMessage: String) {
    val atbash = Atbash()
    atbash.alphabet = AlphabetRU()
    println("Атбаш: ${atbash.encode(encryptMessage)}")
}

fun useCaesar(encryptMessage: String) {
    val caesar = CaesarGronsfeld()
    caesar.alphabet = AlphabetRU()
    for (shiftCount in -32..32) {
        caesar.key = NumKey(shiftCount)
        println("Сдвиг: ${caesar.key} ${caesar.decode(encryptMessage)}")
    }
}

fun useGronsfeld(encryptMessage: String) {
    val gronsfeld = CaesarGronsfeld()
    gronsfeld.alphabet = AlphabetRU("Ё")
    gronsfeld.key = NumKey(1, 4, 3)
    println("Сдвиг: ${gronsfeld.key} ${gronsfeld.decode(encryptMessage)}")
}

fun useVigener(encryptMessage: String) {
    val vigener = Vigener()
    vigener.alphabet = AlphabetRU()
    vigener.key = StringKey("111")
    println("Ключ: ${vigener.key} ${vigener.decode(encryptMessage)}")
}

fun useFrequency(encryptMessage: String) {
    val messFreq = encryptMessage.getFrequencySymbols(alphabetRU)
    val collate = collateFrequency(messFreq, alphabetRU.frequency)
    println(encryptMessage.map { collate[it] }.joinToString(""))
}

fun useRSA(encryptMessage: String) {

    val RSA = RSA()
    RSA.alphabet = AlphabetRU()
    //RSA.key = MyRSA(7,13)
    RSA.key = MyRSA(7,13)
    println("Ключ: ${RSA.key}")

    println()

    var message = "КАФСИ"
    println("message bytes: ${Arrays.toString(message.toByteArray())}")
    println("message: $message")

    println()

    val messageEncrypt = RSA.encode(message.map { alphabetRU.getNumByChar(it).toByte() }.toByteArray())
    println("messageEncrypt bytes: ${Arrays.toString(messageEncrypt)}")
    message = String(messageEncrypt, Charset.defaultCharset())
    println("messageEncrypt: $message")

    println()

    val messageDecrypt = RSA.decode(messageEncrypt)
    println("messageDecrypt bytes: ${Arrays.toString(messageDecrypt)}")
    message = String(messageDecrypt, Charset.defaultCharset())
    println("messageDecrypt: $message")
}

fun applyEncoding(bytes: ByteArray, charset: Charset) {
    println(charset)
    println("String: ${String(bytes, charset)}")
    println("Byte:   ${Arrays.toString(bytes)}")
    println()
}

fun findWords() {

    for (size in 2..10) {
        val words = mutableMapOf<String, Int>()

        for (i in 0..strEncrypt.length - size) {
            val word = strEncrypt.substring(i, i + size)
            println(word)
        }
        println("ciphers.Size window: $size")
        println(words)
        println()
    }
}
