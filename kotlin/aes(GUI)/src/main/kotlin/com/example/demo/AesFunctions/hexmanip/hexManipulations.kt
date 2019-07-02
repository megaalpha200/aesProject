package hexmanip

import java.math.BigInteger

infix fun String.hexor(x: String) : String {
    val resultList = arrayListOf<String>()

    var firstVal = this
    var secondVal = x

    if (firstVal.length > secondVal.length) {
        secondVal = secondVal.padStart(firstVal.length, '0')
    }
    else if (secondVal.length > firstVal.length) {
        firstVal = firstVal.padStart(secondVal.length, '0')
    }

    firstVal.forEachIndexed { index, value ->
        resultList.add(Integer.toHexString(value.fromHexToInt() xor secondVal[index].fromHexToInt()))
    }

    return resultList.joinToString("")
}

infix fun String.hexadd(x: String) : String {
    val firstVal = this
    val secondVal = x
    val firstValInt = BigInteger(firstVal, 16).toInt()
    val secondValInt = BigInteger(secondVal, 16).toInt()

    return Integer.toHexString(firstValInt + secondValInt)
}

infix fun String.hexmod(x: String) : String {
    val firstVal = this
    val secondVal = x
    val firstValInt = BigInteger(firstVal, 16).toInt()
    val secondValInt = BigInteger(secondVal, 16).toInt()

    return Integer.toHexString(firstValInt % secondValInt)
}

fun Char.fromHexToInt() : Int {
    return BigInteger(this.toString(), 16).toInt()
}