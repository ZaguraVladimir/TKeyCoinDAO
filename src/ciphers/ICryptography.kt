package ciphers

interface ICryptography {
    infix fun encode(message: String): String
    infix fun decode(message: String): String
}