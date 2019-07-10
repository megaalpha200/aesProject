package textConversions

import java.math.BigInteger
import org.apache.commons.codec.binary.Base64


fun main() {
    //println(convertHexToString("49206c696b6520636f6d707574657220636c617373"))
    //convertStringToBin("Computer")
}

fun convertStringToHex(input: String) : String {
    //val inputNoSpace = input.replace("\\s".toRegex(), "")
    val hexArrayList = arrayListOf<String>()

    input.forEach {
        var hexInt = it.toInt() % 256
        if (hexInt < 0)
            hexInt += 256

        val hexStr = Integer.toHexString(hexInt).padStart(2, '0')

        hexArrayList.add(hexStr)
    }

    return hexArrayList.joinToString("")
}

fun convertBase64StringToHex(input: String) : String {
    //val inputNoSpace = input.replace("\\s".toRegex(), "")
    val hexArrayList = arrayListOf<String>()

    val encodedInputByteArray = input.toByteArray()
    val decodedInputByteArray = Base64.decodeBase64(encodedInputByteArray)

    decodedInputByteArray.forEach {
        var hexInt = it.toInt() % 265
        if (hexInt < 0)
            hexInt += 256

        val hexStr = Integer.toHexString(hexInt).padStart(2, '0')

        hexArrayList.add(hexStr)
    }

    return hexArrayList.joinToString("")
}

fun convertHexToBin(input: String) : String {
    val chunkedHexList = input.chunked(1)
    val binArrayList = arrayListOf<String>()

    chunkedHexList.forEach { hex ->
        binArrayList.add(BigInteger(hex, 16).toString(2).takeLast(4).padStart(4, '0'))
    }

    return binArrayList.joinToString("")
}

fun convertBinToHex(input: String) : String {
    val chunkedBinList = input.chunked(4)
    val hexArrayList = arrayListOf<String>()

    chunkedBinList.forEach { bin->
        hexArrayList.add(BigInteger(bin, 2).toString(16))
    }

    return hexArrayList.joinToString("") { it.toUpperCase() }
}

fun convertHexToString(input: String) : String {
    val chunkedHexList = input.chunked(2)
    val strArrayList = arrayListOf<Char>()

    chunkedHexList.forEach { hex ->
        strArrayList.add(BigInteger(hex, 16).toInt().toChar())
    }

    return strArrayList.joinToString("")
}

fun convertHexToBase64String(input: String) : String {
    val chunkedHexList = input.chunked(2)
    val strArrayList = arrayListOf<Char>()
    val toBeEncodedByteArrayList = arrayListOf<Byte>()

    chunkedHexList.forEach { hex ->
        toBeEncodedByteArrayList.add(BigInteger(hex, 16).toByte())
    }

    val toBeEncodedByteArray = ByteArray(toBeEncodedByteArrayList.size)
    for (i in 0 until toBeEncodedByteArrayList.size) {
        toBeEncodedByteArray[i] = toBeEncodedByteArrayList[i]
    }

    val encodedByteArray = Base64.encodeBase64(toBeEncodedByteArray)

    encodedByteArray.forEach {
        val intVal = it.toInt()
        val charVal = intVal.toChar()
        strArrayList.add(charVal)
    }

    return strArrayList.joinToString("")
}

fun convertStringToBin(input: String) : String {
    val stASCIIHex = convertStringToHex(input)
    val stASCIIBin = convertHexToBin(stASCIIHex)

    println("Hex: ${stASCIIHex.chunked(2).joinToString(" ")}")
    println("Binary: ${stASCIIBin.chunked(8).joinToString(" ")}")

    return stASCIIBin
}

fun convertBase64StringToBin(input: String) : String {
    val stASCIIHex = convertBase64StringToHex(input)
    val stASCIIBin = convertHexToBin(stASCIIHex)

    println("Hex: ${stASCIIHex.chunked(2).joinToString(" ")}")
    println("Binary: ${stASCIIBin.chunked(8).joinToString(" ")}")

    return stASCIIBin
}