import textConversions.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun main() {
    encryptDecryptMenu()
}

fun encryptDecryptMenu() {
    var cipherMode: CipherMode? = null
    var inputText: String? = null
    var inputKey: String? = null

    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    do {
        try {
            if (cipherMode == null) {
                print(System.lineSeparator())
                println("AES Encryptor/Decryptor")
                println("Using Kotlin/JVM")
                println("Created by: Jose A. Alvarado")
                println("Copyright J.A.A. Productions 2019")

                println()

                println("Please choose an option: ")
                println("\t1. Encrypt")
                println("\t2. Decrypt")
                println("\t3. Quit")
                print("Choice: ")

                when (Integer.parseInt(bufferedReader.readLine())) {
                    1 -> cipherMode = CipherMode.ENCRYPT
                    2 -> cipherMode = CipherMode.DECRYPT
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> throw WrongMenuChoiceException()
                }
            }

            if (inputText == null) {
                print(System.lineSeparator())

                println("Please choose an option: ")
                println("\t1. $cipherMode String ${if (cipherMode == CipherMode.ENCRYPT) "PlainText" else "CipherText"}")
                println("\t2. $cipherMode Hexadecimal ${if (cipherMode == CipherMode.ENCRYPT) "PlainText" else "CipherText"}")
                println("\t3. Quit")
                print("Choice: ")
                var menuOption = Integer.parseInt(bufferedReader.readLine())

                when (menuOption) {
                    1 -> inputText = encryptDecryptInputTextPrep(cipherMode, TextMode.STRING)
                    2 -> inputText = encryptDecryptInputTextPrep(cipherMode, TextMode.HEX)
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> inputText = null
                }

                if (inputText == null)
                    throw WrongMenuChoiceException()

                print(System.lineSeparator())

                println("Please choose an option: ")
                println("\t1. Use String Key")
                println("\t2. Use Hexadecimal Key")
                println("\t3. Quit")
                print("Choice: ")
                menuOption = Integer.parseInt(bufferedReader.readLine())

                when (menuOption) {
                    1 -> inputKey = keyTextPrep(TextMode.STRING)
                    2 -> inputKey = keyTextPrep(TextMode.HEX)
                    3 -> {
                        println("Goodbye!")
                        return
                    }
                    else -> {
                        inputText = null
                        inputKey = null
                    }
                }

                if (inputKey == null)
                    throw WrongMenuChoiceException()
            }

            print(System.lineSeparator())

            if (cipherMode == CipherMode.ENCRYPT)
                encrypt(inputText!!, inputKey!!)
            else if (cipherMode == CipherMode.DECRYPT)
                decrypt(inputText!!, inputKey!!)

            println()

            cipherMode = null
            inputText = null
            inputKey = null
        } catch (e: WrongMenuChoiceException) {
            println(e.toString())
        } catch (e: NumberFormatException) {
            println("$e - Please enter a number!")
        } catch (e: Exception) {
            println(e.toString())
            cipherMode = null
            inputText = null
            inputKey = null
        }

    } while (true)
}

@Throws(IOException::class)
fun encryptDecryptInputTextPrep(cipherMode: CipherMode, textMode: TextMode): String {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    val inputTextTypeStr = if (cipherMode == CipherMode.ENCRYPT) "PlainText" else "CipherText"

    print(System.lineSeparator())
    print("Please enter your $inputTextTypeStr as a $textMode  Value: ")
    val inputText = bufferedReader.readLine()

    when (cipherMode) {
        CipherMode.ENCRYPT -> {
            println("PlainText: $inputText")
            println("PlainText Hex and Binary Representations:")
        }
        CipherMode.DECRYPT -> {
            println("CipherText: $inputText")
            println("CipherText Hex and Binary Representations:")
        }
    }

    var inputTextHex = ""
    var inputTextBin = ""

    when (textMode) {
        TextMode.STRING -> {
            inputTextHex = convertStringToHex(inputText)

            inputTextBin = convertHexToBin(inputTextHex)
            println("Hex: ${inputTextHex.chunked(2).joinToString(" ")}")
            println("Binary: ${inputTextBin.chunked(8).joinToString(" ")}")
        }
        TextMode.HEX -> {
            inputTextHex = inputText.replace("\\s".toRegex(), "")

            inputTextBin = convertHexToBin(inputTextHex)
            println("Hex: ${inputTextHex.chunked(2).joinToString(" ")}")
            println("Binary: ${inputTextBin.chunked(8).joinToString(" ")}")
        }
    }

    print(System.lineSeparator())

    return inputTextHex
}

@Throws(IOException::class)
fun keyTextPrep(textMode: TextMode): String {
    val bufferedReader = BufferedReader(InputStreamReader(System.`in`))

    print("Please enter your key as a $textMode Value: ")
    val initialKey = bufferedReader.readLine()

    print(System.lineSeparator())

    println("Initial Key: $initialKey")
    println("Initial Key Hex and Binary Representations:")

    var initialKeyHex = ""
    var initialKeyBin = ""

    when (textMode) {
        TextMode.STRING -> {
            initialKeyHex = convertStringToHex(initialKey)

            initialKeyBin = convertHexToBin(initialKeyHex)
            println("Hex: ${initialKeyHex.chunked(2).joinToString(" ")}")
            println("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}")
        }
        TextMode.HEX -> {
            initialKeyHex = initialKey.replace("\\s".toRegex(), "")

            initialKeyBin = convertHexToBin(initialKeyHex)
            println("Hex: ${initialKeyHex.chunked(2).joinToString(" ")}")
            println("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}")
        }
    }

    print(System.lineSeparator())

    return initialKeyHex
}

fun encrypt(plainTextHex: String, initialKeyHex: String) {
    val cipherTextHex = aesEncrypt(plainTextHex, initialKeyHex)
    val cipherTextStr = convertHexToString(cipherTextHex)

    println("Final CipherText (Hex): ${cipherTextHex.chunked(2).joinToString(" ")}")
    println("Final CipherText (String): $cipherTextStr")
}

fun decrypt(cipherTextHex: String, initialKeyHex: String) {
    val plainTextHex = aesDecrypt(cipherTextHex, initialKeyHex)
    val plainTextStr = convertHexToString(plainTextHex)

    println("Final PlainText (Hex): ${plainTextHex.chunked(2).joinToString(" ")}")
    println("Final PlainText (String): $plainTextStr")
}

private class WrongMenuChoiceException : Exception() {
    override fun toString(): String {
        return this.javaClass.canonicalName + ": Invalid Choice!"
    }
}