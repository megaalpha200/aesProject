package AesFunctions

import textConversions.*
import AesFunctions.aesMainFunctions.CipherMode
import AesFunctions.aesMainFunctions.TextMode
import AesFunctions.aesMainFunctions.aesDecrypt
import AesFunctions.aesMainFunctions.aesEncrypt
import AesFunctions.aesMainFunctions.debugOutput


object AesMain {
    fun encryptDecryptInputTextPrep(inputText: String, cipherMode: CipherMode, textMode: TextMode): String {
        val inputTextTypeStr = if (cipherMode == CipherMode.ENCRYPT) "PlainText" else "CipherText"

        when (cipherMode) {
            CipherMode.ENCRYPT -> {
                debugOutput.append("PlainText: $inputText${System.lineSeparator()}${System.lineSeparator()}")
                debugOutput.append("PlainText Hex and Binary Representations:${System.lineSeparator()}")
            }
            CipherMode.DECRYPT -> {
                debugOutput.append("CipherText: $inputText")
                debugOutput.append("CipherText Hex and Binary Representations:${System.lineSeparator()}")
            }
        }

        var inputTextHex = ""
        var inputTextBin = ""

        if (cipherMode == aesMainFunctions.CipherMode.ENCRYPT && textMode == aesMainFunctions.TextMode.STRING) {
            inputTextHex = convertStringToHex(inputText)

            inputTextBin = convertHexToBin(inputTextHex)
            debugOutput.append("Hex: ${inputTextHex.chunked(2).joinToString(" ")}${System.lineSeparator()}")
            debugOutput.append("Binary: ${inputTextBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
        }
        else if (cipherMode == aesMainFunctions.CipherMode.DECRYPT && textMode == aesMainFunctions.TextMode.STRING) {
            inputTextHex = convertBase64StringToHex(inputText)

            inputTextBin = convertHexToBin(inputTextHex)
            debugOutput.append("Hex: ${inputTextHex.chunked(2).joinToString(" ")}${System.lineSeparator()}")
            debugOutput.append("Binary: ${inputTextBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
        }
        else if (textMode == aesMainFunctions.TextMode.HEX) {
            inputTextHex = inputText.replace("\\s".toRegex(), "")

            inputTextBin = convertHexToBin(inputTextHex)
            debugOutput.append("Hex: ${inputTextHex.chunked(2).joinToString(" ")}${System.lineSeparator()}")
            debugOutput.append("Binary: ${inputTextBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
        }

        debugOutput.append(System.lineSeparator())

        return inputTextHex
    }

    fun keyTextPrep(initialKey: String, textMode: TextMode): String {
        debugOutput.append(System.lineSeparator())

        debugOutput.append("Initial Key: $initialKey${System.lineSeparator()}")
        debugOutput.append("Initial Key Hex and Binary Representations:${System.lineSeparator()}")

        var initialKeyHex = ""
        var initialKeyBin = ""

        when (textMode) {
            TextMode.STRING -> {
                initialKeyHex = convertStringToHex(initialKey)

                initialKeyBin = convertHexToBin(initialKeyHex)
                debugOutput.append("Hex: ${initialKeyHex.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                debugOutput.append("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
            }
            TextMode.HEX -> {
                initialKeyHex = initialKey.replace("\\s".toRegex(), "")

                initialKeyBin = convertHexToBin(initialKeyHex)
                debugOutput.append("Hex: ${initialKeyHex.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                debugOutput.append("Binary: ${initialKeyBin.chunked(8).joinToString(" ")}${System.lineSeparator()}")
            }
        }

        debugOutput.append(System.lineSeparator())

        return initialKeyHex
    }

    fun encrypt(plainTextHex: String, initialKeyHex: String) : Pair<String, String> {
        val cipherTextHex = aesEncrypt(plainTextHex, initialKeyHex)
        val cipherTextStr = convertHexToBase64String(cipherTextHex)

        val chunkedCipherTextHexStr = cipherTextHex.chunked(2).joinToString(" ")

        debugOutput.append("Final CipherText (Hex): $chunkedCipherTextHexStr${System.lineSeparator()}")
        debugOutput.append("Final CipherText (Base64 String): $cipherTextStr${System.lineSeparator()}")

        return Pair(chunkedCipherTextHexStr, cipherTextStr)
    }

    fun decrypt(cipherTextHex: String, initialKeyHex: String) : Pair<String, String> {
        val plainTextHex = aesDecrypt(cipherTextHex, initialKeyHex)
        val plainTextStr = convertHexToString(plainTextHex)

        val chunkedPlainTextHexStr = plainTextHex.chunked(2).joinToString(" ")

        debugOutput.append("Final PlainText (Hex): $chunkedPlainTextHexStr${System.lineSeparator()}")
        debugOutput.append("Final PlainText (String): $plainTextStr${System.lineSeparator()}")

        return Pair(chunkedPlainTextHexStr, plainTextStr)
    }

    private class WrongMenuChoiceException : Exception() {
        override fun toString(): String {
            return this.javaClass.canonicalName + ": Invalid Choice!"
        }
    }
}