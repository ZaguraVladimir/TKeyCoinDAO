import ciphers.*
import java.nio.charset.Charset
import java.util.*


// Зашифрованное сообщение на странице hrsecuritytkeycoin.pro
// 28 групп по 5 символов + 1 группа в 3 символа = 143 символа
// В исходном коде страницы есть подсказка:
// <!-- meta name="text" content="143" -->
// Это скорее всего ключ, но может быть, что это просто количество символов в сообщении.
// EIKOXNWSXTDDIANCDUTHSTOEOZCXHENIIERRXTSHTADIFCTAXSTLXHIXQIHXCAEKAAENWUEXCKNLESSXXIERTAXDLYHSBITXXHNMXXDKELSHIBEXOTWOETOXXDYXBNRWTYXCCAEXAEIAWBS

/*
Encrypted message
Bytes:	 [69, 73, 75, 79, 88, 78, 87, 83, 88, 84, 68, 68, 73, 65, 78, 67, 68, 85, 84, 72, 83, 84, 79, 69, 79, 90, 67, 88, 72, 69, 78, 73, 73, 69, 82, 82, 88, 84, 83, 72, 84, 65, 68, 73, 70, 67, 84, 65, 88, 83, 84, 76, 88, 72, 73, 88, 81, 73, 72, 88, 67, 65, 69, 75, 65, 65, 69, 78, 87, 85, 69, 88, 67, 75, 78, 76, 69, 83, 83, 88, 88, 73, 69, 82, 84, 65, 88, 68, 76, 89, 72, 83, 66, 73, 84, 88, 88, 72, 78, 77, 88, 88, 68, 75, 69, 76, 83, 72, 73, 66, 69, 88, 79, 84, 87, 79, 69, 84, 79, 88, 88, 68, 89, 88, 66, 78, 82, 87, 84, 89, 88, 67, 67, 65, 69, 88, 65, 69, 73, 65, 87, 66, 83]
UTF-8 string:        EIKOXNWSXTDDIANCDUTHSTOEOZCXHENIIERRXTSHTADIFCTAXSTLXHIXQIHXCAEKAAENWUEXCKNLESSXXIERTAXDLYHSBITXXHNMXXDKELSHIBEXOTWOETOXXDYXBNRWTYXCCAEXAEIAWBS
UTF-16 string:       䕉䭏塎坓塔䑄䥁乃䑕呈協佅佚䍘䡅义䥅剒塔午呁䑉䙃呁塓呌塈䥘光䡘䍁䕋䅁䕎坕䕘䍋乌䕓単塉䕒呁塄䱙䡓䉉员塈乍塘䑋䕌午䥂䕘佔坏䕔佘塄奘䉎剗呙塃䍁䕘䅅䥁坂�
UTF-32 string:       ������������������������������������
US-ASCII string:     EIKOXNWSXTDDIANCDUTHSTOEOZCXHENIIERRXTSHTADIFCTAXSTLXHIXQIHXCAEKAAENWUEXCKNLESSXXIERTAXDLYHSBITXXHNMXXDKELSHIBEXOTWOETOXXDYXBNRWTYXCCAEXAEIAWBS
windows-1251 string: EIKOXNWSXTDDIANCDUTHSTOEOZCXHENIIERRXTSHTADIFCTAXSTLXHIXQIHXCAEKAAENWUEXCKNLESSXXIERTAXDLYHSBITXXHNMXXDKELSHIBEXOTWOETOXXDYXBNRWTYXCCAEXAEIAWBS
IBM866 string:       EIKOXNWSXTDDIANCDUTHSTOEOZCXHENIIERRXTSHTADIFCTAXSTLXHIXQIHXCAEKAAENWUEXCKNLESSXXIERTAXDLYHSBITXXHNMXXDKELSHIBEXOTWOETOXXDYXBNRWTYXCCAEXAEIAWBS

EACH ASSET IN THE BLOCKCHAIN IS CODED WITH A UNIQUE IDENTIFIER BY WHICH THE ASSET CAN BE TRACK MONITOR AND TRADE SELL WWW.TKEY.BIZ/KEYS
Process finished with exit code 0
*/

val strEncrypt = Message(
    """EIKOX NWSXT DDIAN CDUTH STOEO ZCXHE NIIER RXTSH
                            TADIF CTAXS TLXHI XQIHX CAEKA AENWU EXCKN LESSX
                            XIERT AXDLY HSBIT XXHNM XXDKE LSHIB EXOTW OETOX
                            XDYXB NRWTY XCCAE XAEIA WBS"""
        .replace(" ", "")
        .replace("\n", "")
)

val arrEncrypt = strEncrypt.map { it.toInt() }.toIntArray()

fun main(args: Array<String>) {

    show("Encrypted message", arrEncrypt)

    val sb = StringBuilder()
    for (i in 0..12)
        for (j in 0..142 step 13)
            sb.append(strEncrypt.text[i + j])
    print(sb.toString().replace("X", " ").replace("DOT", ".").replace("SLASH", "/"))

//    arrEncryptByte.forEach { print(it.toString(16)) }
//
//    // Сразу воспользуемся ключем
//    // Если применить XOR, то получаем стоку, в которой только кирилица, но она тоже зашифрована, от неё и будем плясать
//
////    println("Decrypted message")
////    println("String:\t $strDecrypt")
////    println("Bytes:\t ${Arrays.toString(strDecrypt.toByteArray())}")
////    println()
    // Так как и текст на картинке приглашения, и строка подсказки из EXIF картинки были на кирилице,
    // то считаем что и зашифрованное сообщение тоже на кирилице.

    //findWords(strEncrypt)
    useCiphers()
}

fun useCiphers() {
    // Атбаш не подошел
    if (false) {
        useAtbash(Alphabets.EN, strEncrypt)
    }

    // Поиграемся со сдвигом по алфавиту, т.н. шифром Цезаря
    if (false) {
        useCaesar(Alphabets.EN, strEncrypt)
    }
    // Цезарь не подошел


    // Поиграемся со сдвигом по алфавиту, т.н. шифром Гронсфельда, в котором ключ состоит не из одной цифры. Это модификация шифра Цезаря
    if (false) {
        useGronsfeld(Alphabets.EN, strEncrypt)
    }
    // Гронсфельд не подошел

    // Поиграемся с шифром Виженера
    if (false) {
        useVigener(Alphabets.EN, strEncrypt)
    }
    // Виженер не подошел, нет ключа

    // Сдвиг ничего не дал, теперь дело за частотным анализом
    if (false) {
        useFrequency(Alphabets.EN, strEncrypt)
    }

    // Поиграемся с RSA
    if (false) {
        useRSA(Alphabets.EN, strEncrypt)
    }
}

fun useAtbash(alphabet: Alphabet, encryptMessage: Message) {
    val atbash = Atbash()
    atbash.alphabet = alphabet
    println("Атбаш: ${atbash.encode(encryptMessage)}")
}

fun useCaesar(alphabet: Alphabet, encryptMessage: Message) {
    val caesar = CaesarGronsfeld()
    caesar.alphabet = alphabet
    for (shiftCount in -32..32) {
        caesar.key = NumKey(shiftCount)
        println("Сдвиг: ${caesar.key} ${caesar.decode(encryptMessage)}")
    }
}

fun useGronsfeld(alphabet: Alphabet, encryptMessage: Message) {
    val gronsfeld = CaesarGronsfeld()
    gronsfeld.alphabet = alphabet
    gronsfeld.key = NumKey(1, 4, 3)
    println("Сдвиг: ${gronsfeld.key} ${gronsfeld.decode(encryptMessage)}")
}

fun useVigener(alphabet: Alphabet, encryptMessage: Message) {
    val vigener = Vigener()
    vigener.alphabet = alphabet
    vigener.key = StringKey("111")
    println("Ключ: ${vigener.key} ${vigener.decode(encryptMessage)}")
}

fun useFrequency(alphabet: Alphabet, encryptMessage: Message) {
    val messFreq = getFrequencySymbols(encryptMessage.text, alphabet)
    val collate = getTableReplacements(messFreq, alphabet.frequency)
    println(encryptMessage.map { collate[it] }.joinToString(""))
}

fun useRSA(alphabet: Alphabet, encryptMessage: Message) {

    val rsa = RSA()
    var keys = MyRSA.getKeyPair(11, 13)
    var (publicKey, privateKey) = keys

    //val messsageNums = Alphabets.EN.stringToNums(encryptMessage)
    val messsageNums = encryptMessage.map { it.toInt() }.toIntArray()
    println("messsageNums: ${Arrays.toString(messsageNums)}")

    val messageDecrypt = rsa.decode(messsageNums, privateKey = privateKey)
    println("messageDecrypt bytes: ${Arrays.toString(messageDecrypt)}")
    val message = alphabet.numsToString(messageDecrypt.map { it.toInt() }.toIntArray())
    println("messageDecrypt: $message")

}

fun applyEncoding(ints: IntArray, charset: Charset) {
    val nameCharset = ("$charset string:").padEnd(20, ' ')
    println("$nameCharset ${String(ints.map { it.toByte() }.toByteArray(), charset)}")
}

fun findWords(strEncrypt: String) {

    for (size in 2..10) {
        var words = mutableMapOf<String, Int>()

        for (i in 0..strEncrypt.length - size) {
            val word = strEncrypt.substring(i, i + size)
            words[word] = (words[word] ?: 0) + 1
        }
        words = words.filter { it.value > 1 }.toMutableMap()
        if (words.isNotEmpty()) {
            println("ciphers.Size window: $size")
            println(words)
        }
    }
}

fun show(text: String, ints: IntArray) {

    println(text)
    println("Bytes:\t ${Arrays.toString(ints)}")
    applyEncoding(ints, Charsets.UTF_8)
    applyEncoding(ints, Charsets.UTF_16)
    applyEncoding(ints, Charsets.UTF_32)
    applyEncoding(ints, Charsets.US_ASCII)
    applyEncoding(ints, charset("windows-1251"))
    applyEncoding(ints, charset("IBM866"))
    println()
}