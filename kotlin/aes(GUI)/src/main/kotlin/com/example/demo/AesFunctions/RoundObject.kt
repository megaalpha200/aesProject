package AesFunctions

class RoundObject(val isPreRound: Boolean = false) {
    var preRoundState: String = ""
    var subBytesState: String = ""
    var shiftRowsState: String = ""
    var mixColumnsState: String = ""
    var addRoundKeyState: String = ""
}