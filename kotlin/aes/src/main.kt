import textConversions.convertHexToString
import textConversions.convertStringToHex

fun main() {
    print("Please enter your plaintext: ")
    val plainText: String = readLine()!!

    println()

    print("Please enter your key: ")
    val initialKey: String = readLine()!!

    print(System.lineSeparator())

    println("PlainText: $plainText")
    println("PlainText Hex Representation:")
    val plainTextHex = convertStringToHex(plainText)
    println(plainTextHex.chunked(2).joinToString(" "))

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex Representation:")
    val initialKeyHex = convertStringToHex(initialKey)
    println(initialKeyHex.padEnd(32,'0').chunked(2).joinToString(" "))

    print(System.lineSeparator())

    val cipherTextHex = aesEncrypt(plainTextHex, initialKeyHex)
    val cipherTextStr = convertHexToString(cipherTextHex)

    println("Final CipherText (Hex): ${cipherTextHex.chunked(2).joinToString(" ")}")
    println("Final CipherText (String): $cipherTextStr")

    print(System.lineSeparator())
    println("AES Encryptor")
    println("Using Kotlin/JVM")
    println("Created by: Jose A. Alvarado")
    println("Copyright J.A.A. Productions 2019")
}