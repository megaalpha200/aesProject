import * as hexmanip from './hexManipulations';
import {RoundObject} from './RoundObject';
import * as aux from './auxFuncs';

const NULL_CONSTANT = "NULL";
const R_CONSTANTS = [NULL_CONSTANT, "01000000", "02000000", "04000000", "08000000", "10000000", "20000000", "40000000", "80000000", "1B000000", "36000000"];

/*--------------------------------Boxes-------------------------------------*/

const SUB_BYTE_S_BOX = [["63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2B", "fe", "d7", "ab", "76"],
                        ["ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"],
                        ["b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"],
                        ["04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"],
                        ["09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"],
                        ["53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"],
                        ["d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"],
                        ["51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"],
                        ["cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"],
                        ["60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"],
                        ["e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"],
                        ["e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"],
                        ["ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"],
                        ["70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"],
                        ["e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"],
                        ["8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"]];

const MIX_COLS_CONSTANT_MATRIX = [["02", "03", "01", "01"],
                                    ["01", "02", "03", "01"],
                                    ["01", "01", "02", "03"],
                                    ["03", "01", "01", "02"]];

const MIX_COLS_E_TABLE = [["01", "03", "05", "0f", "11", "33", "55", "ff", "1a", "2e", "72", "96", "a1", "f8", "13", "35"],
                            ["5f", "e1", "38", "48", "d8", "73", "95", "a4", "f7", "02", "06", "0a", "1e", "22", "66", "aa"],
                            ["e5", "34", "5c", "e4", "37", "59", "eb", "26", "6a", "be", "d9", "70", "90", "ab", "e6", "31"],
                            ["53", "f5", "04", "0c", "14", "3c", "44", "cc", "4f", "d1", "68", "b8", "d3", "6e", "b2", "cd"],
                            ["4c", "d4", "67", "a9", "e0", "3b", "4d", "d7", "62", "a6", "f1", "08", "18", "28", "78", "88"],
                            ["83", "9e", "b9", "d0", "6b", "bd", "dc", "7f", "81", "98", "b3", "ce", "49", "db", "76", "9a"],
                            ["b5", "c4", "57", "f9", "10", "30", "50", "f0", "0b", "1d", "27", "69", "bb", "d6", "61", "a3"],
                            ["fe", "19", "2b", "7d", "87", "92", "ad", "ec", "2f", "71", "93", "ae", "e9", "20", "60", "a0"],
                            ["fb", "16", "3a", "4e", "d2", "6d", "b7", "c2", "5d", "e7", "32", "56", "fa", "15", "3f", "41"],
                            ["c3", "5e", "e2", "3d", "47", "c9", "40", "c0", "5b", "ed", "2c", "74", "9c", "bf", "da", "75"],
                            ["9f", "ba", "d5", "64", "ac", "ef", "2a", "7e", "82", "9d", "bc", "df", "7a", "8e", "89", "80"],
                            ["9b", "b6", "c1", "58", "e8", "23", "65", "af", "ea", "25", "6f", "b1", "c8", "43", "c5", "54"],
                            ["fc", "1f", "21", "63", "a5", "f4", "07", "09", "1b", "2d", "77", "99", "b0", "cb", "46", "ca"],
                            ["45", "cf", "4a", "de", "79", "8b", "86", "91", "a8", "e3", "3e", "42", "c6", "51", "f3", "0e"],
                            ["12", "36", "5a", "ee", "29", "7b", "8d", "8c", "8f", "8a", "85", "94", "a7", "f2", "0d", "17"],
                            ["39", "4b", "dd", "7c", "84", "97", "a2", "fd", "1c", "24", "6c", "b4", "c7", "52", "f6", "01"]];

const MIX_COLS_L_TABLE = [[NULL_CONSTANT, "00", "19", "01", "32", "02", "1a", "c6", "4b", "c7", "1b", "68", "33", "ee", "df", "03"],
                            ["64", "04", "e0", "0e", "34", "8d", "81", "ef", "4c", "71", "08", "c8", "f8", "69", "1c", "c1"],
                            ["7d", "c2", "1d", "b5", "f9", "b9", "27", "6a", "4d", "e4", "a6", "72", "9a", "c9", "09", "78"],
                            ["65", "2f", "8a", "05", "21", "0f", "e1", "24", "12", "f0", "82", "45", "35", "93", "da", "8e"],
                            ["96", "8f", "db", "bd", "36", "d0", "ce", "94", "13", "5c", "d2", "f1", "40", "46", "83", "38"],
                            ["66", "dd", "fd", "30", "bf", "06", "8b", "62", "b3", "25", "e2", "98", "22", "88", "91", "10"],
                            ["7e", "6e", "48", "c3", "a3", "b6", "1e", "42", "3a", "6b", "28", "54", "fa", "85", "3d", "ba"],
                            ["2b", "79", "0a", "15", "9b", "9f", "5e", "ca", "4e", "d4", "ac", "e5", "f3", "73", "a7", "57"],
                            ["af", "58", "a8", "50", "f4", "ea", "d6", "74", "4f", "ae", "e9", "d5", "e7", "e6", "ad", "e8"],
                            ["2c", "d7", "75", "7a", "eb", "16", "0b", "f5", "59", "cb", "5f", "b0", "9c", "a9", "51", "a0"],
                            ["7f", "0c", "f6", "6f", "17", "c4", "49", "ec", "d8", "43", "1f", "2d", "a4", "76", "7b", "b7"],
                            ["cc", "bb", "3e", "5a", "fb", "60", "b1", "86", "3b", "52", "a1", "6c", "aa", "55", "29", "9d"],
                            ["97", "b2", "87", "90", "61", "be", "dc", "fc", "bc", "95", "cf", "cd", "37", "3f", "5b", "d1"],
                            ["53", "39", "84", "3c", "41", "a2", "6d", "47", "14", "2a", "9e", "5d", "56", "f2", "d3", "ab"],
                            ["44", "11", "92", "d9", "23", "20", "2e", "89", "b4", "7c", "b8", "26", "77", "99", "e3", "a5"],
                            ["67", "4a", "ed", "de", "c5", "31", "fe", "18", "0d", "63", "8c", "80", "c0", "f7", "70", "07"]];

/*----------------------------------------------------------------------------*/

export let outputLines = [];

export const aesEncrypt = (plaintext, initialKey) => {
    outputLines = [];

    const plaintextBlocks = aux.string_chop(plaintext, 32);
    const paddedInitialKey = initialKey.padEnd(32, '0');
    const finalCipherTextArray = [];

    plaintextBlocks.forEach((block, blockNum) => {
        block = block.padEnd(32, '0').toString();

        const roundKeys = [paddedInitialKey];
        const roundStates = [];

        outputLines.push({output: `PlainText Hex: ${aux.string_chop(plaintext, 2).join(" ")}`})
        outputLines.push({output: ""});

        outputLines.push({output: `Initial Key Hex: ${aux.string_chop(paddedInitialKey, 2).join(" ")}`})
        outputLines.push({output: ""});

        const initialRoundObject = new RoundObject(true);
        initialRoundObject.preRoundState = preRoundFunction(block, paddedInitialKey);
        roundStates.push(initialRoundObject);

        for(let round = 1; round <= 10; round++) {
            roundKeys.push(generateNextRoundKey(roundKeys[round - 1], R_CONSTANTS[round]));

            outputLines.push({output: `Round ${round} Key: ${aux.string_chop(roundKeys[round], 2).join(" ")}`});

            const tempRoundObject = new RoundObject();
            tempRoundObject.preRoundState = (roundStates[round - 1].isPreRound) ? roundStates[round -1].preRoundState : roundStates[round - 1].addRoundKeyState;

            if (round === 10) {
                roundStates.push(roundFunction(tempRoundObject, roundKeys[round], false));
            }
            else {
                roundStates.push(roundFunction(tempRoundObject, roundKeys[round]));
            }
        }

        outputLines.push({output: ""});

        outputLines.push({output: `For Block ${blockNum + 1}...`});
        outputLines.push({output: `Block State: ${aux.string_chop(block, 2).join(" ")}`});

        outputLines.push({output: ""});

        roundStates.forEach((roundObj, roundNum) => {
            outputLines.push({output: `For Block ${blockNum + 1}, Round ${roundNum}...`});

            if(roundObj.isPreRound) {
                outputLines.push({output: `After Pre-Round: ${aux.string_chop(roundObj.preRoundState, 2).join(" ")}`});
            }
            else {
                outputLines.push({output: `After SubBytes: ${aux.string_chop(roundObj.subBytesState, 2).join(" ")}`});
                outputLines.push({output: `After ShiftRows: ${aux.string_chop(roundObj.shiftRowsState, 2).join(" ")}`});
                outputLines.push({output: `After MixColumns: ${aux.string_chop(roundObj.mixColumnsState, 2).join(" ")}`});
                outputLines.push({output: `After AddRoundKey: ${aux.string_chop(roundObj.addRoundKeyState, 2).join(" ")}`});
            }

            outputLines.push({output: ""});
        });

        finalCipherTextArray.push(roundStates[roundStates.length - 1].addRoundKeyState);
    });

    return finalCipherTextArray.join("");
}

/*--------------------------------Round Functions-------------------------------------*/

const preRoundFunction = (initialState, roundKey) => {
    return hexmanip.hexor(initialState, roundKey);
}

const roundFunction = (roundObj, roundKey, useMixCols = true) => {
    const subBytesState = subWord(roundObj.preRoundState);
    const shiftRowsState = shiftRows(subBytesState);

    let finalStateForRound;

    if(useMixCols) {
        const mixColumnsState = mixColumns(shiftRowsState, MIX_COLS_CONSTANT_MATRIX);
        roundObj.mixColumnsState = mixColumnsState;

        finalStateForRound = hexmanip.hexor(mixColumnsState, roundKey);
    }
    else {
        finalStateForRound = hexmanip.hexor(shiftRowsState, roundKey);
    }

    roundObj.subBytesState = subBytesState;
    roundObj.shiftRowsState = shiftRowsState;
    roundObj.addRoundKeyState = finalStateForRound;

    return roundObj;
}

const shiftRows = (state) => {
    const postShiftRowsStateArray = [];
    const preShiftRowsState = aux.string_chop(rowColOrientStateWords(state), 8);

    for(let offset = 0; offset < preShiftRowsState.length; offset++) {
        postShiftRowsStateArray.push(rotWord(preShiftRowsState[offset], offset));
    }
    return rowColOrientStateWords(postShiftRowsStateArray.join(""));
}

const rowColOrientStateWords = (state) => {
    const rowColOrientedArray = [];
    const chunkedState = aux.string_chop(state, 8, (it) => {
        return aux.string_chop(it, 2);
    });

    for(let i = 0; i < chunkedState.length; i++) {
        chunkedState.forEach((word) => {
            rowColOrientedArray.push(word[i]);
        });
    }

    return rowColOrientedArray.join("");
}

const mixColumns = (state, mixConstant) => {
    const resultState = [];
    const chunkedState = aux.string_chop(state, 8);

    chunkedState.forEach((word) => {
        resultState.push(applyConstantMixCols(word, mixConstant));
    });

    return resultState.join("");
}

const applyConstantMixCols = (word, mixConstant) => {
    const resultWord = [];
    const chunkedWord = aux.string_chop(word, 2);

    mixConstant.forEach((row) => {
        const listToXOR = [];

        row.forEach((constant, index) => {
            const constantLValue = applySBox(constant, MIX_COLS_L_TABLE);
            const wordElementLVal = applySBox(chunkedWord[index], MIX_COLS_L_TABLE);

            if(constantLValue === NULL_CONSTANT || wordElementLVal === NULL_CONSTANT) {
                listToXOR.push("00");
            }
            else {
                const addedLValues = hexmanip.hexadd(constantLValue, wordElementLVal).padStart(2, '0');
                listToXOR.push(applySBox(hexmanip.hexmod(addedLValues, "FF").padStart(2, '0'), MIX_COLS_E_TABLE));
            }
        });

        let finalXORedValue = "00";
        listToXOR.forEach((value) => {
            finalXORedValue = hexmanip.hexor(finalXORedValue, value);
        });

        resultWord.push(finalXORedValue);
    });

    return resultWord.join("");
}

/*------------------------------------------------------------------------------------*/

/*----------------------------------Key Functions-------------------------------------*/

const generateNextRoundKey = (inputKey, roundConstant) => {
    const nextRoundKeyArray = [];

    const chunkedInputKey = aux.string_chop(inputKey, 8);
    const tWord = generateTWord(chunkedInputKey[chunkedInputKey.length - 1], roundConstant);

    let lastWord = tWord;
    chunkedInputKey.forEach((word) => {
        const newWord = hexmanip.hexor(word, lastWord);
        nextRoundKeyArray.push(newWord)
        lastWord = newWord;
    });

    return nextRoundKeyArray.join("");
}

const generateTWord = (lastWord, roundConstant) => {
    const rotatedLastWordFromInput = rotWord(lastWord);
    const subLastWordFromInput = subWord(rotatedLastWordFromInput);

    return hexmanip.hexor(subLastWordFromInput, roundConstant);
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------AUX Functions-------------------------------------*/

const rotWord = (word, offset = 1) => {
    const chunkedWord = aux.string_chop(word, 2);
    const rotatedArray = [];

    for (let i = offset; i < chunkedWord.length; i++) {
        rotatedArray.push(chunkedWord[i]);
    }

    for(let j = 0; j < offset; j++) {
        rotatedArray.push(chunkedWord[j]);
    }

    return rotatedArray.join("");
}

const subWord = (word) => {
    const chunkedWord = aux.string_chop(word, 2);
    const subArray = [];

    chunkedWord.forEach((value) => {
        subArray.push(applySBox(value, SUB_BYTE_S_BOX));
    });

    return subArray.join("");
}

const applySBox = (input, sBox) => { 

    if(input.length !== 2) {
        return NULL_CONSTANT;
    }

    const boxRow = parseInt(input[0], 16);
    const boxCol = parseInt(input[1], 16);

    return sBox[boxRow][boxCol];
}

/*----------------------------------------------------------------------------------*/