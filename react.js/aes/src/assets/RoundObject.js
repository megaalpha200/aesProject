export class RoundObject {
    isPreRound;
    subBytesState;
    shiftRowsState;
    mixColumnsState;
    addRoundKeyState;

    constructor(isPreRound = false) {
        this.isPreRound = isPreRound;
    }
}