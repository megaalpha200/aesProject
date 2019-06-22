import hexmanip.HexManipulations;
import stringmanip.StringManipulations;

import java.util.ArrayList;
import java.util.List;

public class AesMainFunctions {

    public static final String NULL_CONSTANT = "NULL";
    private static String[] R_CONSTANTS = {NULL_CONSTANT, "01000000", "02000000", "04000000", "08000000", "10000000", "20000000", "40000000", "80000000", "1B000000", "36000000"};

    /*--------------------------------Boxes-------------------------------------*/

    public static String[][] SUB_BYTE_S_BOX = {{"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2B", "fe", "d7", "ab", "76"},
                                                {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
                                                {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
                                                {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
                                                {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
                                                {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
                                                {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
                                                {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
                                                {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
                                                {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
                                                {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
                                                {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
                                                {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
                                                {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
                                                {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
                                                {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}};

    public static String[][] INV_SUB_BYTE_S_BOX = {{"52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb"},
                                                    {"7c", "e3", "39", "82", "9b", "2f", "ff", "87", "34", "8e", "43", "44", "c4", "de", "e9", "cb"},
                                                    {"54", "7b", "94", "32", "a6", "c2", "23", "3d", "ee", "4c", "95", "0b", "42", "fa", "c3", "4e"},
                                                    {"08", "2e", "a1", "66", "28", "d9", "24", "b2", "76", "5b", "a2", "49", "6d", "8b", "d1", "25"},
                                                    {"72", "f8", "f6", "64", "86", "68", "98", "16", "d4", "a4", "5c", "cc", "5d", "65", "b6", "92"},
                                                    {"6c", "70", "48", "50", "fd", "ed", "b9", "da", "5e", "15", "46", "57", "a7", "8d", "9d", "84"},
                                                    {"90", "d8", "ab", "00", "8c", "bc", "d3", "0a", "f7", "e4", "58", "05", "b8", "b3", "45", "06"},
                                                    {"d0", "2c", "1e", "8f", "ca", "3f", "0f", "02", "c1", "af", "bd", "03", "01", "13", "8a", "6b"},
                                                    {"3a", "91", "11", "41", "4f", "67", "dc", "ea", "97", "f2", "cf", "ce", "f0", "b4", "e6", "73"},
                                                    {"96", "ac", "74", "22", "e7", "ad", "35", "85", "e2", "f9", "37", "e8", "1c", "75", "df", "6e"},
                                                    {"47", "f1", "1a", "71", "1d", "29", "c5", "89", "6f", "b7", "62", "0e", "aa", "18", "be", "1b"},
                                                    {"fc", "56", "3e", "4b", "c6", "d2", "79", "20", "9a", "db", "c0", "fe", "78", "cd", "5a", "f4"},
                                                    {"1f", "dd", "a8", "33", "88", "07", "c7", "31", "b1", "12", "10", "59", "27", "80", "ec", "5f"},
                                                    {"60", "51", "7f", "a9", "19", "b5", "4a", "0d", "2d", "e5", "7a", "9f", "93", "c9", "9c", "ef"},
                                                    {"a0", "e0", "3b", "4d", "ae", "2a", "f5", "b0", "c8", "eb", "bb", "3c", "83", "53", "99", "61"},
                                                    {"17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"}};

    public static String[][] MIX_COLS_CONSTANT_MATRIX = {{"02", "03", "01", "01"},
                                                        {"01", "02", "03", "01"},
                                                        {"01", "01", "02", "03"},
                                                        {"03", "01", "01", "02"}};

    public static String[][] INV_MIX_COLS_CONSTANT_MATRIX = {{"0e", "0b", "0d", "09"},
                                                            {"09", "0e", "0b", "0d"},
                                                            {"0d", "09", "0e", "0b"},
                                                            {"0b", "0d", "09", "0e"}};

    public static String[][] MIX_COLS_E_TABLE = {{"01", "03", "05", "0f", "11", "33", "55", "ff", "1a", "2e", "72", "96", "a1", "f8", "13", "35"},
                                                {"5f", "e1", "38", "48", "d8", "73", "95", "a4", "f7", "02", "06", "0a", "1e", "22", "66", "aa"},
                                                {"e5", "34", "5c", "e4", "37", "59", "eb", "26", "6a", "be", "d9", "70", "90", "ab", "e6", "31"},
                                                {"53", "f5", "04", "0c", "14", "3c", "44", "cc", "4f", "d1", "68", "b8", "d3", "6e", "b2", "cd"},
                                                {"4c", "d4", "67", "a9", "e0", "3b", "4d", "d7", "62", "a6", "f1", "08", "18", "28", "78", "88"},
                                                {"83", "9e", "b9", "d0", "6b", "bd", "dc", "7f", "81", "98", "b3", "ce", "49", "db", "76", "9a"},
                                                {"b5", "c4", "57", "f9", "10", "30", "50", "f0", "0b", "1d", "27", "69", "bb", "d6", "61", "a3"},
                                                {"fe", "19", "2b", "7d", "87", "92", "ad", "ec", "2f", "71", "93", "ae", "e9", "20", "60", "a0"},
                                                {"fb", "16", "3a", "4e", "d2", "6d", "b7", "c2", "5d", "e7", "32", "56", "fa", "15", "3f", "41"},
                                                {"c3", "5e", "e2", "3d", "47", "c9", "40", "c0", "5b", "ed", "2c", "74", "9c", "bf", "da", "75"},
                                                {"9f", "ba", "d5", "64", "ac", "ef", "2a", "7e", "82", "9d", "bc", "df", "7a", "8e", "89", "80"},
                                                {"9b", "b6", "c1", "58", "e8", "23", "65", "af", "ea", "25", "6f", "b1", "c8", "43", "c5", "54"},
                                                {"fc", "1f", "21", "63", "a5", "f4", "07", "09", "1b", "2d", "77", "99", "b0", "cb", "46", "ca"},
                                                {"45", "cf", "4a", "de", "79", "8b", "86", "91", "a8", "e3", "3e", "42", "c6", "51", "f3", "0e"},
                                                {"12", "36", "5a", "ee", "29", "7b", "8d", "8c", "8f", "8a", "85", "94", "a7", "f2", "0d", "17"},
                                                {"39", "4b", "dd", "7c", "84", "97", "a2", "fd", "1c", "24", "6c", "b4", "c7", "52", "f6", "01"}};

    public static String[][] MIX_COLS_L_TABLE = {{NULL_CONSTANT, "00", "19", "01", "32", "02", "1a", "c6", "4b", "c7", "1b", "68", "33", "ee", "df", "03"},
                                                {"64", "04", "e0", "0e", "34", "8d", "81", "ef", "4c", "71", "08", "c8", "f8", "69", "1c", "c1"},
                                                {"7d", "c2", "1d", "b5", "f9", "b9", "27", "6a", "4d", "e4", "a6", "72", "9a", "c9", "09", "78"},
                                                {"65", "2f", "8a", "05", "21", "0f", "e1", "24", "12", "f0", "82", "45", "35", "93", "da", "8e"},
                                                {"96", "8f", "db", "bd", "36", "d0", "ce", "94", "13", "5c", "d2", "f1", "40", "46", "83", "38"},
                                                {"66", "dd", "fd", "30", "bf", "06", "8b", "62", "b3", "25", "e2", "98", "22", "88", "91", "10"},
                                                {"7e", "6e", "48", "c3", "a3", "b6", "1e", "42", "3a", "6b", "28", "54", "fa", "85", "3d", "ba"},
                                                {"2b", "79", "0a", "15", "9b", "9f", "5e", "ca", "4e", "d4", "ac", "e5", "f3", "73", "a7", "57"},
                                                {"af", "58", "a8", "50", "f4", "ea", "d6", "74", "4f", "ae", "e9", "d5", "e7", "e6", "ad", "e8"},
                                                {"2c", "d7", "75", "7a", "eb", "16", "0b", "f5", "59", "cb", "5f", "b0", "9c", "a9", "51", "a0"},
                                                {"7f", "0c", "f6", "6f", "17", "c4", "49", "ec", "d8", "43", "1f", "2d", "a4", "76", "7b", "b7"},
                                                {"cc", "bb", "3e", "5a", "fb", "60", "b1", "86", "3b", "52", "a1", "6c", "aa", "55", "29", "9d"},
                                                {"97", "b2", "87", "90", "61", "be", "dc", "fc", "bc", "95", "cf", "cd", "37", "3f", "5b", "d1"},
                                                {"53", "39", "84", "3c", "41", "a2", "6d", "47", "14", "2a", "9e", "5d", "56", "f2", "d3", "ab"},
                                                {"44", "11", "92", "d9", "23", "20", "2e", "89", "b4", "7c", "b8", "26", "77", "99", "e3", "a5"},
                                                {"67", "4a", "ed", "de", "c5", "31", "fe", "18", "0d", "63", "8c", "80", "c0", "f7", "70", "07"}};

    /*----------------------------------------------------------------------------*/

    enum Mode {
        ENCRYPT, DECRYPT;

        public String toString() {
            return (this == ENCRYPT) ? "Encrypt" : "Decrypt";
        }
    }

    public static void main(String[] args) {
        //System.out.println(aesDecrypt("ff0b844a0853bf7c6934ab4364148fb9", "0f1571c947d9e8590cb7add6af7f6798"));
    }

    public static String aesEncrypt(String plaintextHex, String initialKeyHex) {
        final List<String> plaintextBlocks = StringManipulations.chunkString(plaintextHex, 32);
        for (int blockIndex = 0; blockIndex < plaintextBlocks.size(); blockIndex++) {
            plaintextBlocks.set(blockIndex, StringManipulations.padRightWithZeros(plaintextBlocks.get(blockIndex), 32));
        }
        final String paddedInitialKey = StringManipulations.padRightWithZeros(initialKeyHex, 32);
        final ArrayList<String> finalCipherTextList = new ArrayList<>();

        int blockNum = 0;
        for (String block : plaintextBlocks) {
            final ArrayList<String> roundKeys = new ArrayList<String>() {{ add(paddedInitialKey); }};
            final ArrayList<RoundObject> roundStates = new ArrayList<>();

            final RoundObject initialRoundObject = new RoundObject(true);
            initialRoundObject.setPreRoundState(preRoundFunction(block, paddedInitialKey));
            roundStates.add(initialRoundObject);

            for (int round = 1; round <= 10; round++) {
                roundKeys.add(generateNextRoundKey(roundKeys.get(round-1), R_CONSTANTS[round]));

                System.out.println("Round " + round + " Key: " + roundKeys.get(round));

                final RoundObject tempRoundObject = new RoundObject();
                if (roundStates.get(round-1).isPreRound())
                    tempRoundObject.setPreRoundState(roundStates.get(round-1).getPreRoundState());
                else
                    tempRoundObject.setPreRoundState(roundStates.get(round-1).getAddRoundKeyState());

                roundStates.add(roundFunction(Mode.ENCRYPT, tempRoundObject, roundKeys.get(round), round != 10));
            }

            System.out.println(System.lineSeparator());

            System.out.println("For Block " + (blockNum + 1) + "...");
            System.out.println("Block State: " + String.join(" ", StringManipulations.chunkString(block, 2)));

            System.out.println(System.lineSeparator());
            displayRoundStates(roundStates, blockNum, Mode.ENCRYPT);

            finalCipherTextList.add(roundStates.get(roundStates.size()-1).getAddRoundKeyState());

            blockNum++;
        }

        return String.join("", finalCipherTextList);
    }

    public static String aesDecrypt(String ciphertextHex, String initialKeyHex) {
        final List<String> ciphertextBlocks = StringManipulations.chunkString(ciphertextHex, 32);
        for (int blockIndex = 0; blockIndex < ciphertextBlocks.size(); blockIndex++) {
            ciphertextBlocks.set(blockIndex, StringManipulations.padRightWithZeros(ciphertextBlocks.get(blockIndex), 32));
        }
        final String paddedInitialKey = StringManipulations.padRightWithZeros(initialKeyHex, 32);
        final ArrayList<String> finalCipherTextList = new ArrayList<>();

        int blockNum = 0;
        for (String block : ciphertextBlocks) {
            final ArrayList<String> roundKeys = new ArrayList<>(11);
            final ArrayList<RoundObject> roundStates = new ArrayList<>();

            while (roundKeys.size() < 11) roundKeys.add(NULL_CONSTANT);
            roundKeys.set(10, paddedInitialKey);

            for (int round = 9; round >= 0; round--) {
                roundKeys.set(round, generateNextRoundKey(roundKeys.get(round+1), R_CONSTANTS[10 - round]));
            }

            final RoundObject initialRoundObject = new RoundObject(true);
            initialRoundObject.setPreRoundState(preRoundFunction(block, roundKeys.get(0)));
            roundStates.add(initialRoundObject);

            for (int round = 1; round <= 10; round++) {
                System.out.println("Round " + round + " Key: " + roundKeys.get(round));

                final RoundObject tempRoundObject = new RoundObject();
                if (roundStates.get(round-1).isPreRound())
                    tempRoundObject.setPreRoundState(roundStates.get(round-1).getPreRoundState());
                else
                    tempRoundObject.setPreRoundState(roundStates.get(round-1).getMixColumnsState());

                roundStates.add(roundFunction(Mode.DECRYPT, tempRoundObject, roundKeys.get(round), round != 10));
            }

            System.out.println(System.lineSeparator());

            System.out.println("For Block " + (blockNum + 1) + "...");
            System.out.println("Block State: " + String.join(" ", StringManipulations.chunkString(block, 2)));

            System.out.println(System.lineSeparator());
            displayRoundStates(roundStates, blockNum, Mode.DECRYPT);

            finalCipherTextList.add(roundStates.get(roundStates.size()-1).getAddRoundKeyState());

            blockNum++;
        }

        return String.join("", finalCipherTextList);
    }

    private static void displayRoundStates(List<RoundObject> roundStates, int blockNum, Mode mode) {
        int roundNum = 0;
        for (RoundObject roundObj : roundStates) {
            System.out.println("For Block " + (blockNum+1) + ", Round " + roundNum + "...");

            if (roundObj.isPreRound())
                System.out.println("After Pre-Round: " + String.join(" ", StringManipulations.chunkString(roundObj.getPreRoundState(), 2)));
            else {
                if (mode == Mode.ENCRYPT) {
                    System.out.println("After SubBytes: " + String.join(" ", StringManipulations.chunkString(roundObj.getSubBytesState(), 2)));
                    System.out.println("After ShiftRows: " + String.join(" ", StringManipulations.chunkString(roundObj.getShiftRowsState(), 2)));
                    System.out.println("After MixColumns: " + String.join(" ", StringManipulations.chunkString(roundObj.getMixColumnsState(), 2)));
                    System.out.println("After AddRoundKey: " + String.join(" ", StringManipulations.chunkString(roundObj.getAddRoundKeyState(), 2)));
                }
                else if (mode == Mode.DECRYPT) {
                    System.out.println("After InvShiftRows: " + String.join(" ", StringManipulations.chunkString(roundObj.getShiftRowsState(), 2)));
                    System.out.println("After InvSubBytes: " + String.join(" ", StringManipulations.chunkString(roundObj.getSubBytesState(), 2)));
                    System.out.println("After AddRoundKey: " + String.join(" ", StringManipulations.chunkString(roundObj.getAddRoundKeyState(), 2)));
                    System.out.println("After InvMixColumns: " + String.join(" ", StringManipulations.chunkString(roundObj.getMixColumnsState(), 2)));
                }
            }

            System.out.println(System.lineSeparator());

            roundNum++;
        }
    }

    /*--------------------------------Round Functions-------------------------------------*/

    private static String preRoundFunction(String initialState, String roundKey) {
        return HexManipulations.hexor(initialState, roundKey);
    }

    private static RoundObject roundFunction(Mode mode, RoundObject roundObj, String roundKey, boolean useMixCols) {
        final String[][] mixColsConstant;
        String subBytesState = "";
        String shiftRowState = "";

        if (mode == Mode.ENCRYPT) {
            mixColsConstant = MIX_COLS_CONSTANT_MATRIX;
            subBytesState = subWord(roundObj.getPreRoundState(), false);
            shiftRowState = shiftRows(subBytesState, false);

            if (useMixCols) {
                final String mixColumnsState = mixColumns(shiftRowState, mixColsConstant);
                roundObj.setMixColumnsState(mixColumnsState);
                roundObj.setAddRoundKeyState(HexManipulations.hexor(mixColumnsState, roundKey));
            }
            else
                roundObj.setAddRoundKeyState(HexManipulations.hexor(shiftRowState, roundKey));
        }
        else if (mode == Mode.DECRYPT) {
            mixColsConstant = INV_MIX_COLS_CONSTANT_MATRIX;
            shiftRowState = shiftRows(roundObj.getPreRoundState(), true);
            subBytesState = subWord(shiftRowState, true);

            if (useMixCols) {
                final String addRoundKeyState = HexManipulations.hexor(subBytesState, roundKey);
                roundObj.setAddRoundKeyState(addRoundKeyState);
                roundObj.setMixColumnsState(mixColumns(addRoundKeyState, mixColsConstant));
            }
            else
                roundObj.setAddRoundKeyState(HexManipulations.hexor(subBytesState, roundKey));
        }

        roundObj.setSubBytesState(subBytesState);
        roundObj.setShiftRowsState(shiftRowState);

        return roundObj;
    }

    private static String shiftRows(String state, boolean isInverse) {
        final ArrayList<String> postShiftRowsStateList = new ArrayList<>();
        final List<String> preShiftRowsState = StringManipulations.chunkString(rowColOrientStateWords(state), 8);

        for (int offset = 0; offset < preShiftRowsState.size(); offset++) {
            if (isInverse)
                postShiftRowsStateList.add(rotWordRight(preShiftRowsState.get(offset), offset));
            else
                postShiftRowsStateList.add(rotWordLeft(preShiftRowsState.get(offset), offset));
        }

        return rowColOrientStateWords(String.join("", postShiftRowsStateList));
    }

    private static String rowColOrientStateWords(String state) {
        final ArrayList<String> rowColOrientedList = new ArrayList<>();
        final List<String> protoChunkedState = StringManipulations.chunkString(state, 8);
        final ArrayList<List<String>> chunkedState = new ArrayList<>();

        for (String protoChunk : protoChunkedState) {
            chunkedState.add(StringManipulations.chunkString(protoChunk, 2));
        }

        for (int index = 0; index < chunkedState.size(); index++) {
            for (List<String> word : chunkedState) {
                rowColOrientedList.add(word.get(index));
            }
        }

        return String.join("", rowColOrientedList);
    }

    private static String mixColumns(String state, String[][] mixConstant) {
        final ArrayList<String> resultState = new ArrayList<>();
        final List<String> chunkedState = StringManipulations.chunkString(state, 8);

        for (String word : chunkedState) {
            resultState.add(applyConstantMixCols(word, mixConstant));
        }

        return String.join("", resultState);
    }

    private static String applyConstantMixCols(String word, String[][] mixConstant) {
        final ArrayList<String> resultWord = new ArrayList<>();
        final List<String> chunkedWord = StringManipulations.chunkString(word, 2);

        for (String[] row : mixConstant) {
            final ArrayList<String> listToXOR = new ArrayList<>();

            int index = 0;
            for (String constant : row) {
                final String constantLValue = applySBox(constant, MIX_COLS_L_TABLE);
                final String wordElementVal = applySBox(chunkedWord.get(index), MIX_COLS_L_TABLE);

                if (constantLValue.equals(NULL_CONSTANT) || wordElementVal.equals(NULL_CONSTANT))
                    listToXOR.add("00");
                else {
                    final String addedLValues = StringManipulations.padLeftWithZeros(HexManipulations.hexadd(constantLValue, wordElementVal), 2);
                    listToXOR.add(applySBox(StringManipulations.padLeftWithZeros(HexManipulations.hexmod(addedLValues, "FF"), 2), MIX_COLS_E_TABLE));
                }

                index++;
            }

            String finalXORedValue = "00";
            for (String value : listToXOR) {
                finalXORedValue = HexManipulations.hexor(finalXORedValue, value);
            }

            resultWord.add(finalXORedValue);
        }

        return String.join("", resultWord);
    }

    /*------------------------------------------------------------------------------------*/

    /*----------------------------------Key Functions-------------------------------------*/

    private static String generateNextRoundKey(String inputKey, String roundConstant) {
        final ArrayList<String> nextRoundKeyList = new ArrayList<>();

        final List<String> chunkedInputKey = StringManipulations.chunkString(inputKey, 8);
        final String tWord = generateTWord(chunkedInputKey.get(chunkedInputKey.size()-1), roundConstant);

        String lastWord = tWord;
        for (String word : chunkedInputKey) {
            final String newWord = HexManipulations.hexor(word, lastWord);
            nextRoundKeyList.add(newWord);
            lastWord = newWord;
        }

        return String.join("", nextRoundKeyList);
    }

    private static String generateTWord(String lastWord, String roundConstant) {
        final String rotatedLastWordFromInput = rotWordLeft(lastWord, 1);
        final String subLastWordListFromInput = subWord(rotatedLastWordFromInput, false);

        return HexManipulations.hexor(subLastWordListFromInput, roundConstant);
    }

    /*------------------------------------------------------------------------------------*/

    /*--------------------------------AUX Functions-------------------------------------*/

    public static String rotWordLeft(String word, int offset) {
        final StringBuilder rotatedStr = new StringBuilder();
        int modOffsetVal = (offset*2) % word.length();

        rotatedStr.append(word.substring(modOffsetVal));
        rotatedStr.append(word.substring(0, modOffsetVal));

        return rotatedStr.toString();
    }

    public static String rotWordRight(String word, int offset) {
        final StringBuilder rotatedStr = new StringBuilder();
        int modOffsetVal = (offset*2) % word.length();

        rotatedStr.append(word.substring(word.length() - modOffsetVal));
        rotatedStr.append(word.substring(0, word.length() - modOffsetVal));

        return rotatedStr.toString();
    }

    public static String subWord(String word, boolean useInverseSubBytes) {
        final List<String> chunkedWord = StringManipulations.chunkString(word, 2);
        final ArrayList<String> subList = new ArrayList<>();

        for (String value : chunkedWord) {
            if (useInverseSubBytes)
                subList.add(applySBox(value, INV_SUB_BYTE_S_BOX));
            else
                subList.add(applySBox(value, SUB_BYTE_S_BOX));
        }

        return String.join("", subList);
    }

    public static String applySBox(String input, String[][] sBox) {
        if (input.length() != 2)
            return NULL_CONSTANT;

        final int boxRow = HexManipulations.fromHexToInt(input.charAt(0));
        final int boxCol = HexManipulations.fromHexToInt(input.charAt(1));

        return sBox[boxRow][boxCol];
    }

    /*----------------------------------------------------------------------------------*/
}
