package ciphers

class RSA {

    fun encode(Message: IntArray, publicKey: MyRSA.RSAKey) = proc(Message, publicKey)

    fun decode(Message: IntArray, privateKey: MyRSA.RSAKey) = proc(Message, privateKey)

    private fun proc(Message: IntArray, key: MyRSA.RSAKey): IntArray =
        Message.map {
            it.toBigInteger().modPow(key.p, key.n).toInt()
        }.toIntArray()
}