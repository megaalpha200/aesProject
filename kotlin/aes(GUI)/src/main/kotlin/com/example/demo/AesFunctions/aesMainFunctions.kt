package AesFunctions

import hexmanip.fromHexToInt
import hexmanip.hexadd
import hexmanip.hexmod
import hexmanip.hexor

object aesMainFunctions {
    private const val NULL_CONSTANT = "NULL"
    val R_CONSTANTS = arrayOf(NULL_CONSTANT, "01000000", "02000000", "04000000", "08000000", "10000000", "20000000", "40000000", "80000000", "1B000000", "36000000")

/*--------------------------------Boxes-------------------------------------*/

    val SUB_BYTE_S_BOX = arrayOf(arrayOf("63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2B", "fe", "d7", "ab", "76"),
                                arrayOf("ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"),
                                arrayOf("b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"),
                                arrayOf("04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"),
                                arrayOf("09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"),
                                arrayOf("53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"),
                                arrayOf("d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"),
                                arrayOf("51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"),
                                arrayOf("cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"),
                                arrayOf("60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"),
                                arrayOf("e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"),
                                arrayOf("e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"),
                                arrayOf("ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"),
                                arrayOf("70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"),
                                arrayOf("e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"),
                                arrayOf("8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"))

    val INV_SUB_BYTE_S_BOX = arrayOf(arrayOf("52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb"),
                                    arrayOf("7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb"),
                                    arrayOf("54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e"),
                                    arrayOf("08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25"),
                                    arrayOf("72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92"),
                                    arrayOf("6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84"),
                                    arrayOf("90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06"),
                                    arrayOf("d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b"),
                                    arrayOf("3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73"),
                                    arrayOf("96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e"),
                                    arrayOf("47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b"),
                                    arrayOf("fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4"),
                                    arrayOf("1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f"),
                                    arrayOf("60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef"),
                                    arrayOf("a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61"),
                                    arrayOf("17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"))

    val MIX_COLS_CONSTANT_MATRIX = arrayOf(arrayOf("02", "03", "01", "01"),
                                            arrayOf("01", "02", "03", "01"),
                                            arrayOf("01", "01", "02", "03"),
                                            arrayOf("03", "01", "01", "02"))

    val INV_MIX_COLS_CONSTANT_MATRIX = arrayOf(arrayOf("0e", "0b", "0d", "09"),
                                                arrayOf("09", "0e", "0b", "0d"),
                                                arrayOf("0d", "09", "0e", "0b"),
                                                arrayOf("0b", "0d", "09", "0e"))

    val MIX_COLS_E_TABLE = arrayOf(arrayOf("01", "03", "05", "0f", "11", "33", "55", "ff", "1a", "2e", "72", "96", "a1", "f8", "13", "35"),
                                    arrayOf("5f", "e1", "38", "48", "d8", "73", "95", "a4", "f7", "02", "06", "0a", "1e", "22", "66", "aa"),
                                    arrayOf("e5", "34", "5c", "e4", "37", "59", "eb", "26", "6a", "be", "d9", "70", "90", "ab", "e6", "31"),
                                    arrayOf("53", "f5", "04", "0c", "14", "3c", "44", "cc", "4f", "d1", "68", "b8", "d3", "6e", "b2", "cd"),
                                    arrayOf("4c", "d4", "67", "a9", "e0", "3b", "4d", "d7", "62", "a6", "f1", "08", "18", "28", "78", "88"),
                                    arrayOf("83", "9e", "b9", "d0", "6b", "bd", "dc", "7f", "81", "98", "b3", "ce", "49", "db", "76", "9a"),
                                    arrayOf("b5", "c4", "57", "f9", "10", "30", "50", "f0", "0b", "1d", "27", "69", "bb", "d6", "61", "a3"),
                                    arrayOf("fe", "19", "2b", "7d", "87", "92", "ad", "ec", "2f", "71", "93", "ae", "e9", "20", "60", "a0"),
                                    arrayOf("fb", "16", "3a", "4e", "d2", "6d", "b7", "c2", "5d", "e7", "32", "56", "fa", "15", "3f", "41"),
                                    arrayOf("c3", "5e", "e2", "3d", "47", "c9", "40", "c0", "5b", "ed", "2c", "74", "9c", "bf", "da", "75"),
                                    arrayOf("9f", "ba", "d5", "64", "ac", "ef", "2a", "7e", "82", "9d", "bc", "df", "7a", "8e", "89", "80"),
                                    arrayOf("9b", "b6", "c1", "58", "e8", "23", "65", "af", "ea", "25", "6f", "b1", "c8", "43", "c5", "54"),
                                    arrayOf("fc", "1f", "21", "63", "a5", "f4", "07", "09", "1b", "2d", "77", "99", "b0", "cb", "46", "ca"),
                                    arrayOf("45", "cf", "4a", "de", "79", "8b", "86", "91", "a8", "e3", "3e", "42", "c6", "51", "f3", "0e"),
                                    arrayOf("12", "36", "5a", "ee", "29", "7b", "8d", "8c", "8f", "8a", "85", "94", "a7", "f2", "0d", "17"),
                                    arrayOf("39", "4b", "dd", "7c", "84", "97", "a2", "fd", "1c", "24", "6c", "b4", "c7", "52", "f6", "01"))

    val MIX_COLS_L_TABLE = arrayOf(arrayOf(NULL_CONSTANT, "00", "19", "01", "32", "02", "1a", "c6", "4b", "c7", "1b", "68", "33", "ee", "df", "03"),
                                    arrayOf("64", "04", "e0", "0e", "34", "8d", "81", "ef", "4c", "71", "08", "c8", "f8", "69", "1c", "c1"),
                                    arrayOf("7d", "c2", "1d", "b5", "f9", "b9", "27", "6a", "4d", "e4", "a6", "72", "9a", "c9", "09", "78"),
                                    arrayOf("65", "2f", "8a", "05", "21", "0f", "e1", "24", "12", "f0", "82", "45", "35", "93", "da", "8e"),
                                    arrayOf("96", "8f", "db", "bd", "36", "d0", "ce", "94", "13", "5c", "d2", "f1", "40", "46", "83", "38"),
                                    arrayOf("66", "dd", "fd", "30", "bf", "06", "8b", "62", "b3", "25", "e2", "98", "22", "88", "91", "10"),
                                    arrayOf("7e", "6e", "48", "c3", "a3", "b6", "1e", "42", "3a", "6b", "28", "54", "fa", "85", "3d", "ba"),
                                    arrayOf("2b", "79", "0a", "15", "9b", "9f", "5e", "ca", "4e", "d4", "ac", "e5", "f3", "73", "a7", "57"),
                                    arrayOf("af", "58", "a8", "50", "f4", "ea", "d6", "74", "4f", "ae", "e9", "d5", "e7", "e6", "ad", "e8"),
                                    arrayOf("2c", "d7", "75", "7a", "eb", "16", "0b", "f5", "59", "cb", "5f", "b0", "9c", "a9", "51", "a0"),
                                    arrayOf("7f", "0c", "f6", "6f", "17", "c4", "49", "ec", "d8", "43", "1f", "2d", "a4", "76", "7b", "b7"),
                                    arrayOf("cc", "bb", "3e", "5a", "fb", "60", "b1", "86", "3b", "52", "a1", "6c", "aa", "55", "29", "9d"),
                                    arrayOf("97", "b2", "87", "90", "61", "be", "dc", "fc", "bc", "95", "cf", "cd", "37", "3f", "5b", "d1"),
                                    arrayOf("53", "39", "84", "3c", "41", "a2", "6d", "47", "14", "2a", "9e", "5d", "56", "f2", "d3", "ab"),
                                    arrayOf("44", "11", "92", "d9", "23", "20", "2e", "89", "b4", "7c", "b8", "26", "77", "99", "e3", "a5"),
                                    arrayOf("67", "4a", "ed", "de", "c5", "31", "fe", "18", "0d", "63", "8c", "80", "c0", "f7", "70", "07"))

/*----------------------------------------------------------------------------*/

    enum class CipherMode
    (value: Int) {
        ENCRYPT(0), DECRYPT(1);

        private val mode = value

        override fun toString() : String {
            return when(mode) {
                0 -> "Encrypt"
                1 -> "Decrypt"
                else -> ""
            }
        }
    }

    enum class TextMode {
        STRING, HEX;

        override fun toString(): String {
            return if (this == STRING) "String" else "Hexadecimal"
        }
    }

    var debugOutput = java.lang.StringBuilder("")

    fun aesEncrypt(plaintextHex: String, initialKeyHex: String) : String {
        val plaintextBlocks = plaintextHex.chunked(32) { it.padEnd(32, '0').toString() }
        val paddedInitialKey = initialKeyHex.padEnd(32, '0').substring(0, 32)
        val finalCipherTextList = arrayListOf<String>()

        plaintextBlocks.forEachIndexed { blockNum, block ->
            val roundKeys = arrayListOf(paddedInitialKey)
            val roundStates = arrayListOf<RoundObject>()

            val initialRoundObject = RoundObject(true)
            initialRoundObject.preRoundState = preRoundFunction(block, paddedInitialKey)
            roundStates.add(initialRoundObject)

            for(round in 1..10) {
                roundKeys.add(generateNextRoundKey(roundKeys[round - 1], R_CONSTANTS[round]))

                debugOutput.append("Round $round Key: ${roundKeys[round]}${System.lineSeparator()}")

                val tempRoundObject = RoundObject()
                tempRoundObject.preRoundState = if (roundStates[round - 1].isPreRound) roundStates[round - 1].preRoundState else roundStates[round - 1].addRoundKeyState

                roundStates.add(roundFunction(CipherMode.ENCRYPT, tempRoundObject, roundKeys[round], round != 10))
            }

            debugOutput.append(System.lineSeparator())

            debugOutput.append("For Block ${blockNum + 1}...${System.lineSeparator()}")
            debugOutput.append("Block State: ${block.chunked(2).joinToString(" ")}${System.lineSeparator()}")

            debugOutput.append(System.lineSeparator())
            displayRoundStates(roundStates, blockNum, CipherMode.ENCRYPT)

            finalCipherTextList.add(roundStates[roundStates.lastIndex].addRoundKeyState)
        }

        //debugOutput.append("Ciphertext: ${roundStates[roundStates.lastIndex].addRoundKeyState.chunked(2).joinToString(" ")}")

        return finalCipherTextList.joinToString("")
    }

    fun aesDecrypt(ciphertextHex: String, initialKeyHex: String) : String {
        val ciphertextBlocks = ciphertextHex.chunked(32) { it.padEnd(32, '0').toString() }
        val paddedInitialKey = initialKeyHex.padEnd(32, '0').substring(0, 32)
        val finalPlainTextList = arrayListOf<String>()

        ciphertextBlocks.forEachIndexed { blockNum, block ->
            val roundKeys = arrayListOf(paddedInitialKey)
            val roundStates = arrayListOf<RoundObject>()

            for (round in 1..10) {
                roundKeys.add(generateNextRoundKey(roundKeys[round - 1], R_CONSTANTS[round]))
            }

            roundKeys.reverse()

            val initialRoundObject = RoundObject(true)
            initialRoundObject.preRoundState = preRoundFunction(block, roundKeys[0])
            roundStates.add(initialRoundObject)

            for(round in 1..10) {
                debugOutput.append("Round $round Key: ${roundKeys[round]}${System.lineSeparator()}")

                val tempRoundObject = RoundObject()
                tempRoundObject.preRoundState = if (roundStates[round - 1].isPreRound) roundStates[round - 1].preRoundState else roundStates[round - 1].mixColumnsState

                if (round == 10)
                    roundStates.add(roundFunction(CipherMode.DECRYPT, tempRoundObject, roundKeys[round], false))
                else
                    roundStates.add(roundFunction(CipherMode.DECRYPT, tempRoundObject, roundKeys[round]))
            }

            debugOutput.append(System.lineSeparator())

            debugOutput.append("For Block ${blockNum + 1}...${System.lineSeparator()}")
            debugOutput.append("Block State: ${block.chunked(2).joinToString(" ")}${System.lineSeparator()}")

            debugOutput.append(System.lineSeparator())
            displayRoundStates(roundStates, blockNum, CipherMode.DECRYPT)

            finalPlainTextList.add(roundStates[roundStates.lastIndex].addRoundKeyState)
        }

        //debugOutput.append("Ciphertext: ${roundStates[roundStates.lastIndex].addRoundKeyState.chunked(2).joinToString(" ")}")

        return finalPlainTextList.joinToString("")
    }

    private fun displayRoundStates(roundStates: List<RoundObject>, blockNum: Int, cipherMode: CipherMode) {
        roundStates.forEachIndexed { roundNum, roundObj ->
            debugOutput.append("For Block ${blockNum + 1}, Round $roundNum...${System.lineSeparator()}")

            if (roundObj.isPreRound)
                debugOutput.append("After Pre-Round: ${roundObj.preRoundState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
            else {
                when(cipherMode) {
                    CipherMode.ENCRYPT -> {
                        debugOutput.append("After SubBytes: ${roundObj.subBytesState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                        debugOutput.append("After ShiftRows: ${roundObj.shiftRowsState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                        debugOutput.append("After MixColumns: ${roundObj.mixColumnsState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                        debugOutput.append("After AddRoundKey: ${roundObj.addRoundKeyState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                    }
                    CipherMode.DECRYPT -> {
                        debugOutput.append("After InvShiftRows: ${roundObj.shiftRowsState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                        debugOutput.append("After InvSubBytes: ${roundObj.subBytesState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                        debugOutput.append("After AddRoundKey: ${roundObj.addRoundKeyState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                        debugOutput.append("After InvMixColumns: ${roundObj.mixColumnsState.chunked(2).joinToString(" ")}${System.lineSeparator()}")
                    }
                }
            }

            debugOutput.append(System.lineSeparator())
        }
    }

/*--------------------------------Round Functions-------------------------------------*/

    private fun preRoundFunction(initialState: String, roundKey: String) : String {
        return initialState hexor roundKey
    }

    private fun roundFunction(cipherMode: CipherMode, roundObj: RoundObject, roundKey: String, useMixCols: Boolean = true) : RoundObject {
        val mixColsConstant = when(cipherMode) {
            CipherMode.ENCRYPT -> MIX_COLS_CONSTANT_MATRIX
            CipherMode.DECRYPT -> INV_MIX_COLS_CONSTANT_MATRIX
        }

        val subBytesState: String
        val shiftRowState: String

        when(cipherMode) {
            CipherMode.ENCRYPT -> {
                subBytesState = subWord(roundObj.preRoundState)
                shiftRowState = shiftRows(subBytesState)
            }
            CipherMode.DECRYPT -> {
                shiftRowState = shiftRows(roundObj.preRoundState, true)
                subBytesState = subWord(shiftRowState, true)
            }
        }

        roundObj.subBytesState = subBytesState
        roundObj.shiftRowsState = shiftRowState

        when(cipherMode) {
            CipherMode.ENCRYPT -> {
                if (useMixCols) {
                    val mixColumnsState = mixColumns(shiftRowState, mixColsConstant)
                    roundObj.mixColumnsState = mixColumnsState
                    roundObj.addRoundKeyState = mixColumnsState hexor roundKey
                }
                else {
                    roundObj.addRoundKeyState = shiftRowState hexor roundKey
                }
            }

            CipherMode.DECRYPT -> {
                if (useMixCols) {
                    val addRoundKeyState = subBytesState hexor roundKey
                    roundObj.addRoundKeyState = addRoundKeyState
                    roundObj.mixColumnsState = mixColumns(addRoundKeyState, mixColsConstant)
                }
                else {
                    roundObj.addRoundKeyState = subBytesState hexor roundKey
                }
            }
        }

        //debugOutput.append(mixColumnsState.chunked(8) { it.chunked(2) })
        //debugOutput.append(finalStateForRound.chunked(8) { it.chunked(2) })

        return roundObj
    }

    private fun shiftRows(state: String, isInverse: Boolean = false) : String {
        val postShiftRowsStateList = arrayListOf<String>()
        val preShiftRowsState = rowColOrientStateWords(state).chunked(8)

        for (offset in 0 until preShiftRowsState.size) {
            if (isInverse)
                postShiftRowsStateList.add(rotWordRight(preShiftRowsState[offset], offset))
            else
                postShiftRowsStateList.add(rotWordLeft(preShiftRowsState[offset], offset))
        }

        return rowColOrientStateWords(postShiftRowsStateList.joinToString(""))
    }

    private fun rowColOrientStateWords(state: String) : String {
        val rowColOrientedList = arrayListOf<String>()
        val chunkedState = state.chunked(8) { it.chunked(2) }

        for (index in 0 until chunkedState.size) {
            chunkedState.forEach { word ->
                rowColOrientedList.add(word[index])
            }
        }

        return rowColOrientedList.joinToString("")
    }

    private fun mixColumns(state: String, mixConstant: Array<Array<String>>) : String {
        val resultState = arrayListOf<String>()
        val chunkedState = state.chunked(8)

        chunkedState.forEach { word ->
            resultState.add(applyConstantMixCols(word, mixConstant))
        }

        return resultState.joinToString("")
    }

    private fun applyConstantMixCols(word: String, mixConstant: Array<Array<String>>) : String {
        val resultWord = arrayListOf<String>()
        val chunkedWord = word.chunked(2)

        mixConstant.forEach { row ->
            val listToXOR = arrayListOf<String>()

            row.forEachIndexed { index, constant ->
                val constantLValue = applySBox(constant, MIX_COLS_L_TABLE)
                val wordElementLVal = applySBox(chunkedWord[index], MIX_COLS_L_TABLE)

                if (constantLValue == NULL_CONSTANT || wordElementLVal == NULL_CONSTANT)
                    listToXOR.add("00")
                else {
                    val addedLValues = (constantLValue hexadd wordElementLVal).padStart(2, '0')
                    listToXOR.add(applySBox((addedLValues hexmod "FF").padStart(2, '0'), MIX_COLS_E_TABLE))
                }
            }

            var finalXORedValue = "00"
            listToXOR.forEach { value ->
                finalXORedValue = finalXORedValue hexor value
            }

            resultWord.add(finalXORedValue)
        }

        return resultWord.joinToString("")
    }

/*------------------------------------------------------------------------------------*/

/*----------------------------------Key Functions-------------------------------------*/

    private fun generateNextRoundKey(inputKey: String, roundConstant: String) : String {
        val nextRoundKeyList = arrayListOf<String>()

        val chunkedInputKey = inputKey.chunked(8)
        val tWord = generateTWord(chunkedInputKey[chunkedInputKey.lastIndex], roundConstant)

        var lastWord = tWord
        chunkedInputKey.forEach { word ->
            val newWord = word hexor lastWord
            nextRoundKeyList.add(newWord)
            lastWord = newWord
        }

        return nextRoundKeyList.joinToString("")
    }

    private fun generateTWord(lastWord: String, roundConstant: String) : String {
        val rotatedLastWordFromInput = rotWordLeft(lastWord)
        val subLastWordListFromInput = subWord(rotatedLastWordFromInput)

        return subLastWordListFromInput hexor roundConstant
    }

/*------------------------------------------------------------------------------------*/

/*--------------------------------AUX Functions-------------------------------------*/

    fun rotWordLeft(word: String, offset: Int = 1): String {
        val rotatedStr = StringBuilder("")
        val modOffsetVal = offset * 2 % word.length

        rotatedStr.append(word.substring(modOffsetVal))
        rotatedStr.append(word.substring(0, modOffsetVal))

        return rotatedStr.toString()
    }

    fun rotWordRight(word: String, offset: Int = 1): String {
        val rotatedStr = StringBuilder("")
        val modOffsetVal = offset * 2 % word.length

        rotatedStr.append(word.substring(word.length - modOffsetVal))
        rotatedStr.append(word.substring(0, word.length - modOffsetVal))

        return rotatedStr.toString()
    }

    fun subWord(word: String, useInverseSubBytes: Boolean = false) : String {
        val chunkedWord = word.chunked(2)
        val subList = arrayListOf<String>()

        chunkedWord.forEach { value ->
            if (useInverseSubBytes)
                subList.add(applySBox(value, INV_SUB_BYTE_S_BOX))
            else
                subList.add(applySBox(value, SUB_BYTE_S_BOX))
        }

        return subList.joinToString("")
    }

    fun applySBox(input: String, sBox: Array<Array<String>>) : String {
        if (input.length != 2)
            return NULL_CONSTANT

        val boxRow = input[0].fromHexToInt()
        val boxCol = input[1].fromHexToInt()

        return sBox[boxRow][boxCol]
    }

/*----------------------------------------------------------------------------------*/
}