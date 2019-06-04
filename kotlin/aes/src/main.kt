import textConversions.convertHexToBin
import textConversions.convertHexToString
import textConversions.convertStringToHex

fun main() {
    var isChoiceValid: Boolean

    do {
        println("Please choose an option: ")
        println("\t1. Encrypt")
        println("\t2. Decrypt")
        print("Choice: ")

        isChoiceValid = when (readLine()!!.toInt()) {
            1 -> { encryptDecryptMenu(Mode.ENCRYPT); true; }
            2 -> { encryptDecryptMenu(Mode.DECRYPT); true; }
            else -> false
        }

        if (!isChoiceValid) {
            println("Invalid Choice!")
            print(System.lineSeparator())
        }

    } while(!isChoiceValid)

    print(System.lineSeparator())
    println("AES Encryptor/Decryptor")
    println("Using Kotlin/JVM")
    println("Created by: Jose A. Alvarado")
    println("Copyright J.A.A. Productions 2019")
}

fun encryptDecryptMenu(mode: Mode) {
    var isChoiceValid: Boolean
    var inputTextAndKeyPair: Pair<String, String>?
    var swapLastRound: Boolean?

    do {
        print(System.lineSeparator())

        println("Please choose an option: ")
        println("\t1. $mode String ${if (mode == Mode.ENCRYPT) "PlainText" else if (mode == Mode.DECRYPT) "CipherText" else ""}")
        println("\t2. $mode Hexadecimal ${if (mode == Mode.ENCRYPT) "PlainText" else if (mode == Mode.DECRYPT) "CipherText" else ""}")
        print("Choice: ")
        val menuOption = readLine()!!.toInt()

        inputTextAndKeyPair = when(menuOption) {
            1 -> encryptDecryptStringPlainTextPrep(mode)
            2 -> encryptDecryptHexPlainTextPrep(mode)
            else -> null
        }

        isChoiceValid = inputTextAndKeyPair != null
        if (!isChoiceValid)
            println("Invalid Choice!")

    } while(!isChoiceValid)

    print(System.lineSeparator())

    when (mode) {
        Mode.ENCRYPT -> encrypt(inputTextAndKeyPair!!.first, inputTextAndKeyPair.second)
        Mode.DECRYPT -> decrypt(inputTextAndKeyPair!!.first, inputTextAndKeyPair.second)
    }
}

fun encryptDecryptStringPlainTextPrep(mode: Mode) : Pair<String, String> {
    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> print("Please enter your plaintext as a String: ")
        Mode.DECRYPT -> print("Please enter your ciphertext as a String: ")
    }

    val inputText: String = readLine()!!

    println()

    print("Please enter your key as a String: ")
    val initialKey: String = readLine()!!

    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> {
            println("PlainText: $inputText")
            println("PlainText Hex and Binary Representations:")
        }
        Mode.DECRYPT -> {
            println("CipherText: $inputText")
            println("CipherText Hex and Binary Representations:")
        }
    }

    val inputTextHex = convertStringToHex(inputText)
    println("Hex: ${inputTextHex.chunked(2).joinToString(" ")}")
    println("Binary: ${convertHexToBin(inputTextHex).chunked(8).joinToString(" ")}")

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")
    val initialKeyHex = convertStringToHex(initialKey)
    println("Hex: ${initialKeyHex.chunked(2).joinToString(" ")}")
    println("Binary: ${convertHexToBin(initialKeyHex).chunked(8).joinToString(" ")}")

    print(System.lineSeparator())

    return Pair(inputTextHex, initialKeyHex)
}

fun encryptDecryptHexPlainTextPrep(mode: Mode) : Pair<String, String> {
    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> print("Please enter your plaintext as a Hex Value: ")
        Mode.DECRYPT -> print("Please enter your ciphertext as a Hex Value: ")
    }

    val inputText: String = readLine()!!.replace("\\s".toRegex(), "")

    println()

    print("Please enter your key as a Hex Value: ")
    val initialKey: String = readLine()!!

    print(System.lineSeparator())

    when(mode) {
        Mode.ENCRYPT -> {
            println("PlainText: $inputText")
            println("PlainText Hex and Binary Representations:")
        }
        Mode.DECRYPT -> {
            println("CipherText: $inputText")
            println("CipherText Hex and Binary Representations:")
        }
    }

    val InputTextHex = inputText
    println("Hex: ${inputText.chunked(2).joinToString(" ")}")
    println("Binary: ${convertHexToBin(InputTextHex).chunked(8).joinToString(" ")}")

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")
    val initialKeyHex = initialKey
    println("Hex: ${initialKey.chunked(2).joinToString(" ")}")
    println("Binary: ${convertHexToBin(initialKeyHex).chunked(8).joinToString(" ")}")

    print(System.lineSeparator())

    return Pair(InputTextHex, initialKeyHex)
}

fun encrypt(plainTextBin: String, initialKeyBin: String) {
    val cipherTextHex = aesEncrypt(plainTextBin, initialKeyBin)
    val cipherTextStr = convertHexToString(cipherTextHex)

    println("Final CipherText (Hex): ${cipherTextHex.chunked(2).joinToString(" ")}")
    println("Final CipherText (String): $cipherTextStr")
}

fun decrypt(cipherTextBin: String, initialKeyBin: String) {
    val plainTextHex = aesDecrypt(cipherTextBin, initialKeyBin)
    val plainTextStr = convertHexToString(plainTextHex)

    println("Final PlainText (Hex): ${plainTextHex.chunked(2).joinToString(" ")}")
    println("Final PlainText (String): $plainTextStr")
}