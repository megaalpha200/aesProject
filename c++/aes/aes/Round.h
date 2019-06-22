#pragma once
#ifndef ROUND_H
#define ROUND_H

#include <string>
using namespace std;

class RoundObject
{
private:
	string preRoundState = "";
	string subBytesState = "";
	string shiftRowsState = "";
	string mixColumnsState = "";
	string addRoundKeyState = "";
	bool isPreRound = false;

public:
	RoundObject() { this->isPreRound = false; }
	RoundObject(bool isPreRound) { this->isPreRound = isPreRound; }
	string getPreRoundState() { return this->preRoundState; }
	void setPreRoundState(string preRoundState) { this->preRoundState = preRoundState; }
	string getSubBytesState() { return subBytesState; }
	void setSubBytesState(string subBytesState){ this->subBytesState = subBytesState; }
	string getShiftRowsState() { return shiftRowsState; }
	void setShiftRowsState(string shiftRowsState) { this->shiftRowsState = shiftRowsState; }
	string getMixColumnsState() { return mixColumnsState; }
	void setMixColumnsState(string mixColumnsState) { this->mixColumnsState = mixColumnsState; }
	string getAddRoundKeyState() { return addRoundKeyState; }
	void setAddRoundKeyState(string addRoundKeyState) { this->addRoundKeyState = addRoundKeyState; }
	bool checkIfPreRound() { return this->isPreRound; }
};

#endif // !ROUND_H
