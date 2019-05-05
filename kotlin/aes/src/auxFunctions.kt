import hexmanip.fromHexToInt

const val NULL_CONSTANT = "NULL"

fun applySBox(input: String, sBox: Array<Array<String>>) : String {
    if (input.length != 2)
        return NULL_CONSTANT

    val boxRow = input[0].fromHexToInt()
    val boxCol = input[1].fromHexToInt()

    return sBox[boxRow][boxCol]
}