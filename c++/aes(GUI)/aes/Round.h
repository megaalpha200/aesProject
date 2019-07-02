#pragma once
#ifndef ROUND_H
#define ROUND_H

#include <string>

class RoundObject
{
private:
	std::string preRoundState = "";
	std::string subBytesState = "";
	std::string shiftRowsState = "";
	std::string mixColumnsState = "";
	std::string addRoundKeyState = "";
	bool isPreRound = false;

public:
	RoundObject() { this->isPreRound = false; }
	RoundObject(bool isPreRound) { this->isPreRound = isPreRound; }
	std::string getPreRoundState() { return this->preRoundState; }
	void setPreRoundState(std::string preRoundState) { this->preRoundState = preRoundState; }
	std::string getSubBytesState() { return subBytesState; }
	void setSubBytesState(std::string subBytesState){ this->subBytesState = subBytesState; }
	std::string getShiftRowsState() { return shiftRowsState; }
	void setShiftRowsState(std::string shiftRowsState) { this->shiftRowsState = shiftRowsState; }
	std::string getMixColumnsState() { return mixColumnsState; }
	void setMixColumnsState(std::string mixColumnsState) { this->mixColumnsState = mixColumnsState; }
	std::string getAddRoundKeyState() { return addRoundKeyState; }
	void setAddRoundKeyState(std::string addRoundKeyState) { this->addRoundKeyState = addRoundKeyState; }
	bool checkIfPreRound() { return this->isPreRound; }
};

#endif // !ROUND_H
