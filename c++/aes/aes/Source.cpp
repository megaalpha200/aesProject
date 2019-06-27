#include <iostream>
#include <iomanip>
#include <string>
#include <bitset>
#include <vector>
#include <sstream>
#include <fstream>
#include <math.h>
#include <exception>
#include <algorithm>
#include "Round.h"
using namespace std;

const string NULL_CONSTANT = "NULL";
string R_CONSTANTS[] = { NULL_CONSTANT, "01000000", "02000000", "04000000", "08000000", "10000000", "20000000", "40000000", "80000000", "1B000000", "36000000" };

/*--------------------------------S-Boxes-------------------------------------------------------------------------------------------------------------*/

const vector<vector<string>> SUB_BYTE_S_BOX = { {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2B", "fe", "d7", "ab", "76"},
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
												{"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"} };

const vector<vector<string>> INV_SUB_BYTE_S_BOX = { {"52", "09", "6a", "d5", "30", "36", "a5", "38", "bf", "40", "a3", "9e", "81", "f3", "d7", "fb"},
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
													{"17", "2b", "04", "7e", "ba", "77", "d6", "26", "e1", "69", "14", "63", "55", "21", "0c", "7d"} };

const vector<vector<string>> MIX_COLS_CONSTANT_MATRIX = { {"02", "03", "01", "01"},
														{"01", "02", "03", "01"},
														{"01", "01", "02", "03"},
														{"03", "01", "01", "02"} };

const vector<vector<string>> INV_MIX_COLS_CONSTANT_MATRIX = { {"0e", "0b", "0d", "09"},
															{"09", "0e", "0b", "0d"},
															{"0d", "09", "0e", "0b"},
															{"0b", "0d", "09", "0e"} };

const vector<vector<string>> MIX_COLS_E_TABLE = { {"01", "03", "05", "0f", "11", "33", "55", "ff", "1a", "2e", "72", "96", "a1", "f8", "13", "35"},
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
												{"39", "4b", "dd", "7c", "84", "97", "a2", "fd", "1c", "24", "6c", "b4", "c7", "52", "f6", "01"} };

const vector<vector<string>> MIX_COLS_L_TABLE = { {NULL_CONSTANT, "00", "19", "01", "32", "02", "1a", "c6", "4b", "c7", "1b", "68", "33", "ee", "df", "03"},
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
												{"67", "4a", "ed", "de", "c5", "31", "fe", "18", "0d", "63", "8c", "80", "c0", "f7", "70", "07"} };

/*----------------------------------------------------------------------------------------------------------------------------------------------------*/

const char BASE2[] = { "01" };

enum class Mode { ENCRYPT, DECRYPT, NONE };

/*--------------------------------Function Prototypes-------------------------------------*/

void encryptDecryptMenu();
void encryptDecryptStringInputTextPrep(Mode mode, pair<string, string> &inputTextAndKeyPair);
void encryptDecryptHexInputTextPrep(Mode, pair<string, string>&);
void encrypt(string, string);
void decrypt(string, string);
string aesEncrypt(string, string);
string aesDecrypt(string, string);
void displayRoundStates(vector<RoundObject>&, int, Mode);
string preRoundFunction(string, string);
void roundFunction(Mode, RoundObject&, string, bool = true);
string shiftRows(string, bool = false);
string rowColOrientStateWords(string);
string mixColumns(string, const vector<vector<string>>&);
string applyConstantMixCols(string, const vector<vector<string>>&);
string generateNextRoundKey(string, string);
string generateTWord(string, string);
string rotWordLeft(string, int = 1);
string rotWordRight(string, int = 1);
string subWord(string, bool = false);
string applySBox(string, const vector<vector<string>>&);
void chunkString(string, int, vector<string>&);
char* reverseConstString(char const*);
string padLeftWithZeros(string, int);
string padRightWithZeros(string, int);
string hexor(string, string);
string hexadd(string, string);
string hexmod(string, string);
int fromHexToInt(char);
string convertBin(int, char* = nullptr, int = 1, bool = true);
string convertStringToHex(string);
string convertHexToBin(string);
string convertBinToHex(string);
string convertHexToString(string);
string convertStringToBin(string);
string joinToString(vector<string>&, string);
string chunkAndJoinString(string, string, int);
void removeSpaces(string&);

/*----------------------------------------------------------------------------------------*/

/*----------------------------------Program Main Functions-------------------------------------*/

class WrongMenuChoiceException : public exception
{
public:
	virtual const char* what() const throw()
	{
		return "Invalid Choice!";
	}
} wrongChoiceEx;

fstream outputStream;

int main()
{
	outputStream = fstream("debug.txt", ios::out);
	encryptDecryptMenu();
	outputStream.close();

	cout << endl;
	system("pause");
	return 0;
}

void encryptDecryptMenu()
{
	Mode mode = Mode::NONE;
	pair<string, string> inputTextAndKeyPair;

	bool isPairInitialized = false;

	string userInput;
	string modeStr;

	do
	{
		try
		{
			outputStream = fstream("debug.txt", ios::out | ios::app);

			if (mode == Mode::NONE)
			{
				cout << endl;
				cout << "AES Encryptor/Decryptor" << endl;
				cout << "Using C++" << endl;
				cout << "Created by Jose A. Alvarado" << endl;
				cout << "Copyright J.A.A. Productions 2019" << endl;

				cout << endl;

				cout << "Please choose an option: " << endl;
				cout << "\t1. Encrypt" << endl;
				cout << "\t2. Decrypt" << endl;
				cout << "\t3. Quit" << endl;
				cout << "Choice: ";

				getline(cin, userInput);
				switch (stoi(userInput, nullptr))
				{
				case 1:
					mode = Mode::ENCRYPT;
					modeStr = "Encrypt";
					break;
				case 2:
					mode = Mode::DECRYPT;
					modeStr = "Decrypt";
					break;
				case 3:
					cout << "Goodbye!" << endl;
					return;
					break;
				default:
					mode = Mode::NONE;
					throw wrongChoiceEx;
					break;
				}
			}

			if (!isPairInitialized)
			{
				cout << endl;

				cout << "Please choose an option: " << endl;
				cout << "\t1. " << modeStr << " String " << ((mode == Mode::ENCRYPT) ? "PlainText" : "CipherText") << endl;
				cout << "\t2. " << modeStr << " Hexidecimal " << ((mode == Mode::ENCRYPT) ? "PlainText" : "CipherText") << endl;
				cout << "\t3. Quit" << endl;
				cout << "Choice: ";

				getline(cin, userInput);
				switch (stoi(userInput, nullptr))
				{
				case 1:
					encryptDecryptStringInputTextPrep(mode, inputTextAndKeyPair);
					isPairInitialized = true;
					break;
				case 2:
					encryptDecryptHexInputTextPrep(mode, inputTextAndKeyPair);
					isPairInitialized = true;
					break;
				case 3:
					cout << "Goodbye!" << endl;
					return;
					break;
				default:
					isPairInitialized = false;
					throw wrongChoiceEx;
					break;
				}
			}

			cout << endl;

			if (mode == Mode::ENCRYPT)
				encrypt(inputTextAndKeyPair.first, inputTextAndKeyPair.second);
			else if (mode == Mode::DECRYPT)
				decrypt(inputTextAndKeyPair.first, inputTextAndKeyPair.second);

			cout << endl;

			mode = Mode::NONE;
			isPairInitialized = false;
		}
		catch (WrongMenuChoiceException e)
		{
			cout << e.what() << endl;
		}
		catch (exception e)
		{
			cout << e.what() << endl << endl;
			mode = Mode::NONE;
			isPairInitialized = false;
		}

		char full[_MAX_PATH];
		_fullpath(full, "debug.txt", _MAX_PATH);
		cout << "Debug Details Found At: " << full << endl << endl;

		outputStream << endl << endl << endl << endl;
		outputStream.flush();
		outputStream.close();

	} while (true);
}

void encryptDecryptStringInputTextPrep(Mode mode, pair<string, string> &inputTextAndKeyPair)
{
	cout << endl;
	cout << "Please enter your " << ((mode == Mode::ENCRYPT) ? "plaintext" : "ciphertext") << " as a String: ";

	string inputText;
	getline(cin, inputText);

	cout << endl;

	cout << "Please enter your key as a String: ";
	string initialKey;
	getline(cin, initialKey);

	cout << endl;

	switch (mode)
	{
	case Mode::ENCRYPT:
		cout << "PlainText: " << inputText << endl;
		cout << "PlainText Hex and Binary Representations:" << endl;
		outputStream << "PlainText: " << inputText << endl;
		outputStream << "PlainText Hex and Binary Representations:" << endl;
		break;
	case Mode::DECRYPT:
		cout << "CipherText: " << inputText << endl;
		cout << "CipherText Hex and Binary Representations:" << endl;
		outputStream << "CipherText: " << inputText << endl;
		outputStream << "CipherText Hex and Binary Representations:" << endl;
		break;
	default:
		throw exception("An unexpected error has occured!");
		break;
	}

	string inputTextHex = convertStringToHex(inputText);
	cout << "Hex: " << chunkAndJoinString(inputTextHex, " ", 2) << endl;
	cout << "Binary: " << chunkAndJoinString(convertHexToBin(inputTextHex), " ", 8) << endl;
	outputStream << "Hex: " << chunkAndJoinString(inputTextHex, " ", 2) << endl;
	outputStream << "Binary: " << chunkAndJoinString(convertHexToBin(inputTextHex), " ", 8) << endl;

	cout << endl;
	outputStream << endl;

	cout << "Initial Key: " << initialKey << endl;
	cout << "Initial Key Hex and Binary Representations:" << endl;
	outputStream << "Initial Key: " << initialKey << endl;
	outputStream << "Initial Key Hex and Binary Representations:" << endl;

	string initialKeyHex = convertStringToHex(initialKey);
	cout << "Hex: " << chunkAndJoinString(initialKeyHex, " ", 2) << endl;
	cout << "Binary: " << chunkAndJoinString(convertHexToBin(initialKeyHex), " ", 8) << endl;
	outputStream << "Hex: " << chunkAndJoinString(initialKeyHex, " ", 2) << endl;
	outputStream << "Binary: " << chunkAndJoinString(convertHexToBin(initialKeyHex), " ", 8) << endl;

	cout << endl;
	outputStream << endl;

	inputTextAndKeyPair = pair<string, string>(inputTextHex, initialKeyHex);

	return;
}

void encryptDecryptHexInputTextPrep(Mode mode, pair<string, string> &inputTextAndKeyPair)
{
	cout << endl;
	cout << "Please enter your " << ((mode == Mode::ENCRYPT) ? "plaintext" : "ciphertext") << " as a Hex Value: ";

	string inputText;
	getline(cin, inputText);
	removeSpaces(inputText);

	cout << endl;

	cout << "Please enter your key as a Hex Value: ";
	string initialKey;
	getline(cin, initialKey);
	removeSpaces(initialKey);

	cout << endl;

	switch (mode)
	{
	case Mode::ENCRYPT:
		cout << "PlainText: " << inputText << endl;
		cout << "PlainText Hex and Binary Representations:" << endl;
		outputStream << "PlainText: " << inputText << endl;
		outputStream << "PlainText Hex and Binary Representations:" << endl;
		break;
	case Mode::DECRYPT:
		cout << "CipherText: " << inputText << endl;
		cout << "CipherText Hex and Binary Representations:" << endl;
		outputStream << "CipherText: " << inputText << endl;
		outputStream << "CipherText Hex and Binary Representations:" << endl;
		break;
	default:
		throw exception("An unexpected error has occured!");
		break;
	}

	string inputTextHex = inputText;

	cout << "Hex: " << chunkAndJoinString(inputTextHex, " ", 2) << endl;
	cout << "Binary: " << chunkAndJoinString(convertHexToBin(inputTextHex), " ", 8) << endl;
	outputStream << "Hex: " << chunkAndJoinString(inputTextHex, " ", 2) << endl;
	outputStream << "Binary: " << chunkAndJoinString(convertHexToBin(inputTextHex), " ", 8) << endl;

	cout << endl;
	outputStream << endl;

	cout << "Initial Key: " << initialKey << endl;
	cout << "Initial Key Hex and Binary Representations:" << endl;
	outputStream << "Initial Key: " << initialKey << endl;
	outputStream << "Initial Key Hex and Binary Representations:" << endl;

	string initialKeyHex = initialKey;
	cout << "Hex: " << chunkAndJoinString(initialKeyHex, " ", 2) << endl;
	cout << "Binary: " << chunkAndJoinString(convertHexToBin(initialKeyHex), " ", 8) << endl;
	outputStream << "Hex: " << chunkAndJoinString(initialKeyHex, " ", 2) << endl;
	outputStream << "Binary: " << chunkAndJoinString(convertHexToBin(initialKeyHex), " ", 8) << endl;

	cout << endl;
	outputStream << endl;

	inputTextAndKeyPair = pair<string, string>(inputTextHex, initialKeyHex);

	return;
}

void encrypt(string plainTextHex, string initialKeyHex)
{
	string cipherTextHex = aesEncrypt(plainTextHex, initialKeyHex);
	string cipherTextStr = convertHexToString(cipherTextHex);

	cout << "Final CipherText (Hex): " << chunkAndJoinString(cipherTextHex, " ", 2) << endl;
	cout << "Final CipherText (String): " << cipherTextStr << endl;

	outputStream << "Final CipherText (Hex): " << chunkAndJoinString(cipherTextHex, " ", 2) << endl;
	outputStream << "Final CipherText (String): " << cipherTextStr << endl;
}

void decrypt(string cipherTextHex, string initialKeyHex)
{
	string plainTextHex = aesDecrypt(cipherTextHex, initialKeyHex);
	string plainTextStr = convertHexToString(plainTextHex);

	cout << "Final PlainText (Hex): " << chunkAndJoinString(plainTextHex, " ", 2) << endl;
	cout << "Final PlainText (String): " << plainTextStr << endl;

	outputStream << "Final PlainText (Hex): " << chunkAndJoinString(plainTextHex, " ", 2) << endl;
	outputStream << "Final PlainText (String): " << plainTextStr << endl;
}

/*---------------------------------------------------------------------------------------------*/

/*----------------------------------AES Main Functions-------------------------------------*/

string aesEncrypt(string plaintextHex, string initialKeyHex)
{
	vector<string> plaintextBlocks;
	chunkString(plaintextHex, 32, plaintextBlocks);
	for (int blockIndex = 0; blockIndex < plaintextBlocks.size(); blockIndex++)
	{
		plaintextBlocks[blockIndex] = padRightWithZeros(plaintextBlocks[blockIndex], 32);
	}
	string paddedInitialKey = padRightWithZeros(initialKeyHex, 32).substr(0, 32);
	string finalCipherTextStr = "";

	int blockNum = 0;
	for (string block : plaintextBlocks)
	{
		vector<string> roundKeys = {paddedInitialKey};
		vector<RoundObject> roundStates;

		RoundObject intitialRoundObject = new RoundObject(true);
		intitialRoundObject.setPreRoundState(preRoundFunction(block, paddedInitialKey));
		roundStates.push_back(intitialRoundObject);

		for (int round = 1; round <= 10; round++)
		{
			roundKeys.push_back(generateNextRoundKey(roundKeys[round - 1], R_CONSTANTS[round]));

			outputStream << "Round " << round << " Key: " << roundKeys[round] << endl;

			RoundObject tempRoundObject;
			if (roundStates[round - 1].checkIfPreRound())
				tempRoundObject.setPreRoundState(roundStates[round - 1].getPreRoundState());
			else
				tempRoundObject.setPreRoundState(roundStates[round - 1].getAddRoundKeyState());

			roundFunction(Mode::ENCRYPT, tempRoundObject, roundKeys[round], round != 10);
			roundStates.push_back(tempRoundObject);
		}

		outputStream << endl << endl;
		outputStream << "For Block " << (blockNum + 1) << "..." << endl;

		outputStream << "Block State: " << chunkAndJoinString(block, " ", 2);

		outputStream << endl << endl;
		displayRoundStates(roundStates, blockNum, Mode::ENCRYPT);

		finalCipherTextStr += roundStates[roundStates.size() - 1].getAddRoundKeyState();

		blockNum++;
	}

	return finalCipherTextStr;
}

string aesDecrypt(string ciphertextHex, string initialKeyHex)
{
	vector<string> ciphetextBlocks;
	chunkString(ciphertextHex, 32, ciphetextBlocks);
	for (int blockIndex = 0; blockIndex < ciphetextBlocks.size(); blockIndex++)
	{
		ciphetextBlocks[blockIndex] = padRightWithZeros(ciphetextBlocks[blockIndex], 32);
	}
	string paddedInitialKey = padRightWithZeros(initialKeyHex, 32).substr(0, 32);
	string finalCipherTextStr = "";

	int blockNum = 0;
	for (string block : ciphetextBlocks)
	{
		vector<RoundObject> roundStates;
		vector <string> roundKeys;
		while (roundKeys.size() < 11) roundKeys.push_back(NULL_CONSTANT);
		roundKeys[10] = paddedInitialKey;

		for (int round = 9; round >= 0; round--)
		{
			roundKeys[round] = generateNextRoundKey(roundKeys[round + 1], R_CONSTANTS[10 - round]);
		}

		RoundObject initialRoundObject = new RoundObject(true);
		initialRoundObject.setPreRoundState(preRoundFunction(block, roundKeys[0]));
		roundStates.push_back(initialRoundObject);

		for (int round = 1; round <= 10; round++)
		{
			outputStream << "Round " << round << " Key: " << roundKeys[round];

			RoundObject tempRoundObject;
			if (roundStates[round - 1].checkIfPreRound())
				tempRoundObject.setPreRoundState(roundStates[round - 1].getPreRoundState());
			else
				tempRoundObject.setPreRoundState(roundStates[round - 1].getMixColumnsState());

			roundFunction(Mode::DECRYPT, tempRoundObject, roundKeys[round], round != 10);
			roundStates.push_back(tempRoundObject);
		}

		outputStream << endl << endl;

		outputStream << "For Block " << (blockNum + 1) << "..." << endl;
		outputStream << "Block State: " << chunkAndJoinString(block, " ", 2);

		outputStream << endl << endl;
		displayRoundStates(roundStates, blockNum, Mode::DECRYPT);

		finalCipherTextStr += roundStates[roundStates.size() - 1].getAddRoundKeyState();

		blockNum++;
	}

	return finalCipherTextStr;
}

void displayRoundStates(vector<RoundObject> &roundStates, int blockNum, Mode mode)
{
	int roundNum = 0;
	for (RoundObject roundObj : roundStates)
	{
		outputStream << "For Block " << (blockNum + 1) << " Round " << roundNum << "..." << endl;

		if (roundObj.checkIfPreRound())
			outputStream << "After Pre-Round: " << chunkAndJoinString(roundObj.getPreRoundState(), " ", 2);
		else
		{
			if (mode == Mode::ENCRYPT)
			{
				outputStream << "After SubBytes: " << chunkAndJoinString(roundObj.getSubBytesState(), " ", 2) << endl;
				outputStream << "After ShiftRows: " << chunkAndJoinString(roundObj.getShiftRowsState(), " ", 2) << endl;
				outputStream << "After MixColumns: " << chunkAndJoinString(roundObj.getMixColumnsState(), " ", 2) << endl;
				outputStream << "After AddRoundKey: " << chunkAndJoinString(roundObj.getAddRoundKeyState(), " ", 2) << endl;
			}
			else if (mode == Mode::DECRYPT)
			{
				outputStream << "After InvShiftRows: " << chunkAndJoinString(roundObj.getShiftRowsState(), " ", 2) << endl;
				outputStream << "After InvSubBytes: " << chunkAndJoinString(roundObj.getSubBytesState(), " ", 2) << endl;
				outputStream << "After AddRoundKey: " << chunkAndJoinString(roundObj.getAddRoundKeyState(), " ", 2) << endl;
				outputStream << "After InvMixColumns: " << chunkAndJoinString(roundObj.getMixColumnsState(), " ", 2) << endl;
			}
		}

		outputStream << endl << endl;
		roundNum++;
	}
}

/*-----------------------------------------------------------------------------------------*/

/*--------------------------------Round Functions-------------------------------------*/

string preRoundFunction(string initialState, string roundKey)
{
	return hexor(initialState, roundKey);
}

void roundFunction(Mode mode, RoundObject &roundObj, string roundKey, bool useMixCols)
{
	vector<vector<string>> mixColsConstant;
	string subBytesState = "";
	string shiftRowState = "";

	if (mode == Mode::ENCRYPT)
	{
		mixColsConstant = MIX_COLS_CONSTANT_MATRIX;
		subBytesState = subWord(roundObj.getPreRoundState());
		shiftRowState = shiftRows(subBytesState);

		if (useMixCols)
		{
			string mixColumnsState = mixColumns(shiftRowState, mixColsConstant);
			roundObj.setMixColumnsState(mixColumnsState);
			roundObj.setAddRoundKeyState(hexor(mixColumnsState, roundKey));
		}
		else
			roundObj.setAddRoundKeyState(hexor(shiftRowState, roundKey));
	}
	else if (mode == Mode::DECRYPT)
	{
		mixColsConstant = INV_MIX_COLS_CONSTANT_MATRIX;
		shiftRowState = shiftRows(roundObj.getPreRoundState(), true);
		subBytesState = subWord(shiftRowState, true);

		if (useMixCols)
		{
			string addRoundKeyState = hexor(subBytesState, roundKey);
			roundObj.setAddRoundKeyState(addRoundKeyState);
			roundObj.setMixColumnsState(mixColumns(addRoundKeyState, mixColsConstant));
		}
		else
			roundObj.setAddRoundKeyState(hexor(subBytesState, roundKey));
	}

	roundObj.setSubBytesState(subBytesState);
	roundObj.setShiftRowsState(shiftRowState);

	return;
}

string shiftRows(string state, bool isInverse)
{
	string postShiftRowsStateStr = "";

	vector<string> preShiftRowsState;
	chunkString(rowColOrientStateWords(state), 8, preShiftRowsState);

	for (int offset = 0; offset < preShiftRowsState.size(); offset++)
	{
		if (isInverse)
			postShiftRowsStateStr += rotWordRight(preShiftRowsState[offset], offset);
		else
			postShiftRowsStateStr += rotWordLeft(preShiftRowsState[offset], offset);
	}

	return rowColOrientStateWords(postShiftRowsStateStr);
}

string rowColOrientStateWords(string state)
{
	string rowColOrientedStr = "";

	vector<string> protoChunkedState;
	vector<vector<string>> chunkedState;
	chunkString(state, 8, protoChunkedState);
	for (int i = 0; i < protoChunkedState.size(); i++)
	{
		chunkedState.push_back(vector<string>());
		chunkString(protoChunkedState[i], 2, chunkedState[i]);
	}

	for (int index = 0; index < chunkedState.size(); index++)
	{
		for (vector<string> word : chunkedState)
		{
			rowColOrientedStr += word[index];
		}
	}

	return rowColOrientedStr;
}

string mixColumns(string state, const vector<vector<string>> &mixConstant)
{
	string resultState = "";

	vector<string> chunkedState;
	chunkString(state, 8, chunkedState);

	for (string word : chunkedState)
	{
		resultState += applyConstantMixCols(word, mixConstant);
	}

	return resultState;
}

string applyConstantMixCols(string word, const vector<vector<string>> &mixConstant)
{
	string resultWord = "";

	vector<string> chunkedWord;
	chunkString(word, 2, chunkedWord);

	for (vector<string> row : mixConstant)
	{
		vector<string> listToXOR;

		int index = 0;
		for (string constant : row)
		{
			string constantLValue = applySBox(constant, MIX_COLS_L_TABLE);
			string wordElementVal = applySBox(chunkedWord[index], MIX_COLS_L_TABLE);

			if (constantLValue == NULL_CONSTANT || wordElementVal == NULL_CONSTANT)
				listToXOR.push_back("00");
			else
			{
				string addedLValues = padLeftWithZeros(hexadd(constantLValue, wordElementVal), 2);
				listToXOR.push_back(applySBox(padLeftWithZeros(hexmod(addedLValues, "FF"), 2), MIX_COLS_E_TABLE));
			}

			index++;
		}

		string finalXORedValue = "00";
		for (string value : listToXOR)
		{
			finalXORedValue = hexor(finalXORedValue, value);
		}

		resultWord += finalXORedValue;
	}

	return resultWord;
}

/*------------------------------------------------------------------------------------*/

/*----------------------------------Key Functions-------------------------------------*/

string generateNextRoundKey(string inputKey, string roundConstant)
{
	string nextRoundKey = "";

	vector<string> chunkedInputKey;
	chunkString(inputKey, 8, chunkedInputKey);
	string tWord = generateTWord(chunkedInputKey[chunkedInputKey.size() - 1], roundConstant);

	string lastWord = tWord;
	for (string word : chunkedInputKey)
	{
		string newWord = hexor(word, lastWord);
		nextRoundKey += newWord;
		lastWord = newWord;
	}

	return nextRoundKey;
}

string generateTWord(string lastWord, string roundConstant)
{
	string rotatedLastWordFromInput = rotWordLeft(lastWord);
	string subLastWordListFromInput = subWord(rotatedLastWordFromInput, false);

	return hexor(subLastWordListFromInput, roundConstant);
}

/*------------------------------------------------------------------------------------*/

/*--------------------------------AUX Functions-------------------------------------*/

string rotWordLeft(string word, int offset)
{
	int modOffsetVal = (offset * 2) % word.length();
	string rotatedStr = "";

	rotatedStr += word.substr(modOffsetVal);
	rotatedStr += word.substr(0, modOffsetVal);
	
	return rotatedStr;
}
string rotWordRight(string word, int offset)
{
	string rotatedStr = "";
	int modOffsetVal = (offset * 2) % word.length();

	rotatedStr += word.substr(word.length() - modOffsetVal);
	rotatedStr += word.substr(0, word.length() - modOffsetVal);

	return rotatedStr;
}

string subWord(string word, bool useInverseSubBytes)
{
	vector<string> chunkedWord;
	chunkString(word, 2, chunkedWord);

	string subStr = "";

	for (string value : chunkedWord)
	{
		if (useInverseSubBytes)
			subStr += applySBox(value, INV_SUB_BYTE_S_BOX);
		else
			subStr += applySBox(value, SUB_BYTE_S_BOX);
	}

	return subStr;
}

string applySBox(string input, const vector<vector<string>> &sBox)
{
	if (input.length() != 2)
		return NULL_CONSTANT;

	int boxRow = fromHexToInt(input[0]);
	int boxCol = fromHexToInt(input[1]);

	return sBox[boxRow][boxCol];
}

/*----------------------------------------------------------------------------------*/

/*------------------------------------------------Hex Manipulation Functions------------------------------------------------*/

string hexor(string x1, string x2)
{
	string finalResult = "";

	string firstVal = x1;
	string secondVal = x2;

	if (firstVal.length() > secondVal.length())
	{
		secondVal = padLeftWithZeros(secondVal, firstVal.length());
	}
	else if (secondVal.length() > firstVal.length())
	{
		firstVal = padLeftWithZeros(firstVal, secondVal.length());
	}

	for (int i = 0; i < firstVal.length(); i++)
	{
		stringstream ss;
		char firstValTempChar = firstVal[i];
		char secondValTempChar = secondVal[i];
		int hexOrRes = fromHexToInt(firstValTempChar) ^ fromHexToInt(secondValTempChar);

		ss << hex << hexOrRes;
		finalResult += ss.str();
	}

	return finalResult;
}

string hexadd(string x1, string x2)
{
	string firstVal = x1;
	string secondVal = x2;
	int firstValInt = stoi(firstVal, nullptr, 16);
	int secondValInt = stoi(secondVal, nullptr, 16);

	stringstream ss;
	ss << hex << (firstValInt + secondValInt);

	return ss.str();
}

string hexmod(string x1, string x2)
{
	string firstVal = x1;
	string secondVal = x2;
	int firstValInt = stoi(firstVal, nullptr, 16);
	int secondValInt = stoi(secondVal, nullptr, 16);

	stringstream ss;
	ss << hex << (firstValInt % secondValInt);

	return ss.str();
}

int fromHexToInt(char x)
{
	char *tempStr = new char[2];
	tempStr[0] = x;
	tempStr[1] = NULL;

	return stoi(tempStr, nullptr, 16);
}

/*--------------------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------String Manipulation Functions------------------------------------------------*/
void chunkString(string s, int size, vector<string> &chunkedList)
{
	int startIndex = 0;
	int endIndex = size;

	while (endIndex <= s.length())
	{
		chunkedList.push_back(s.substr(startIndex, size));
		startIndex += size;
		endIndex += size;
	}

	if (endIndex > s.length())
	{
		int diff = endIndex - s.length();
		string subStr = s.substr(startIndex, (size - diff));
		if (subStr != "")
			chunkedList.push_back(subStr);
	}
}

char* reverseConstString(char const* str)
{
	// find length of string 
	int n = strlen(str);

	// create dynamic pointer char array 
	char *rev = new char[n + 1];

	// copy of string to ptr array 
	strcpy_s(rev, n + 1, str);

	// Swap character starting from two 
	// corners 
	for (int i = 0, j = n - 1; i < j; i++, j--)
		swap(rev[i], rev[j]);

	// return pointer of reversed string 
	return rev;
}

string padLeftWithZeros(string s, int amount)
{
	stringstream ss;
	string paddedStr;

	if (s.length() < amount)
	{
		ss << "" << setfill('0') << setw(amount) << s;
		getline(ss, paddedStr);
	}
	else
		paddedStr = s;

	return paddedStr;
}

string padRightWithZeros(string s, int amount)
{
	stringstream ss;
	string paddedStr;

	if (s.length() < amount)
	{
		ss << s << setw(amount - s.length()) << setfill('0') << "";
		getline(ss, paddedStr);
	}
	else
		paddedStr = s;

	return paddedStr;
}

string joinToString(vector<string> &list, string delimiter)
{
	string finalStr;

	int count = 0;
	for (const string &listStr : list)
	{
		finalStr += listStr;

		count++;
		if (count != list.size())
			finalStr += delimiter;
	}

	return finalStr;
}

string chunkAndJoinString(string s, string delimiter, int size)
{
	vector<string> tempChunkedStr;
	chunkString(s, size, tempChunkedStr);
	
	return joinToString(tempChunkedStr, delimiter);
}

void removeSpaces(string &input)
{
	stringstream ss;
	string finalStr = "";

	for (char c : input)
	{
		if (!isspace(c))
			finalStr += c;
	}

	input = finalStr;
}
/*-----------------------------------------------------------------------------------------------------------------------------*/

/*------------------------------------------------Text Conversion Functions------------------------------------------------*/

string convertBin(int num, char *buildNum, int placeVal, bool initial)
{
	int diff = num;
	int pVal = placeVal;
	int pCount = log2(pVal);
	char rem;

	if (buildNum == nullptr)
	{
		buildNum = new char[1];
		buildNum[0] = NULL;
	}

	char *tempPtr = buildNum;

	if (strlen(buildNum) == 0)
	{
		while (pVal < num)
		{
			pVal *= 2;
		}

		if (pVal > num)
		{
			pVal /= 2;
		}
	}
	else if (pVal != 0)
		pVal /= 2;

	pCount = log2(pVal);

	if (pVal != 0)
	{
		if (pVal > diff)
			rem = BASE2[0];
		else
		{
			diff -= pVal;
			rem = BASE2[1];
		}

		*tempPtr = rem;
		tempPtr++;
		convertBin(diff, tempPtr, pVal, false);
	}
	else
	{
		pCount = 0;

		if (strlen(buildNum) == 0)
			buildNum[0] = '0';
	}

	if (initial)
	{
		buildNum[pCount + 1] = NULL;
		return string(buildNum);
	}

	return "";
}

string convertStringToHex(string input)
{
	string finalHexStr = "";

	for (char c : input)
	{
		stringstream ss;
		int charAsInt = (int)c;
		ss << hex << charAsInt;
		finalHexStr += ss.str();
	}

	return finalHexStr;
}

string convertHexToBin(string input)
{
	string binStr = "";

	for (int i = 0; i < input.length(); i++)
	{
		string hex = input.substr(i, 1);
		string hexInBinary = padLeftWithZeros(convertBin(stoi(hex, nullptr, 16)), 4);

		binStr += hexInBinary;
	}

	return binStr;
}

string convertBinToHex(string input)
{
	stringstream ss;

	vector<string> chunkedBinList;
	chunkString(input, 4, chunkedBinList);

	for (string bin : chunkedBinList)
	{
		int inputBase10 = stoll(bin, nullptr, 2);
		ss << hex << inputBase10;
	}

	return ss.str();
}

string convertHexToString(string input)
{
	vector<string> chunkedHexList;
	string finalStr = "";

	chunkString(input, 2, chunkedHexList);

	for (string hex : chunkedHexList)
	{
		char convertedChar = (char)stoi(hex, nullptr, 16);
		finalStr += convertedChar;
	}

	return finalStr;
}

string convertStringToBin(string input)
{
	string stASCIIHex = convertStringToHex(input);
	string stASCIIBin = convertHexToBin(stASCIIHex);

	vector<string> stASCIIHexList;
	vector<string> stASCIIBinList;
	chunkString(stASCIIHex, 2, stASCIIHexList);
	chunkString(stASCIIBin, 8, stASCIIBinList);

	cout << "Hex: " << joinToString(stASCIIHexList, " ") << endl;
	cout << "Binary: " << joinToString(stASCIIBinList, " ") << endl;
	outputStream << "Hex: " << joinToString(stASCIIHexList, " ") << endl;
	outputStream << "Binary: " << joinToString(stASCIIBinList, " ") << endl;

	return stASCIIBin;
}
/*-------------------------------------------------------------------------------------------------------------------------*/