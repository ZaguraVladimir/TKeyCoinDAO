package ciphers

interface ICryptography {
    infix fun encode(message: Message): Message
    infix fun decode(message: Message): Message
}