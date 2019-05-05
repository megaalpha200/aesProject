package textConversions

import java.math.BigInteger

fun convertStringToHex(input: String) : String {
    //val inputNoSpace = input.replace("\\s".toRegex(), "")
    val hexArrayList = arrayListOf<String>()

    input.forEach {
        hexArrayList.add(Integer.toHexString(it.toInt()))
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
    val chunckedBinList = input.chunked(4)
    val hexArrayList = arrayListOf<String>()

    chunckedBinList.forEach { bin->
        hexArrayList.add(BigInteger(bin, 2).toString(16))
    }

    return hexArrayList.joinToString("") { it.capitalize() }
}

fun convertHexToString(input: String) : String {
    val chunkedHexList = input.chunked(2)
    val strArrayList = arrayListOf<Char>()

    chunkedHexList.forEach { hex ->
        strArrayList.add(BigInteger(hex, 16).toInt().toChar())
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
