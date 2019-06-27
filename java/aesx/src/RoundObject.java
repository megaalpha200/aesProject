public class RoundObject {
    private String preRoundState = "";
    private String subBytesState = "";
    private String shiftRowsState = "";
    private String mixColumnsState = "";
    private String addRoundKeyState = "";
    private boolean isPreRound = false;

    public RoundObject() {}

    public RoundObject(boolean isPreRound) {
        this.isPreRound = isPreRound;
    }

    public String getPreRoundState() {
        return preRoundState;
    }

    public void setPreRoundState(String preRoundState) {
        this.preRoundState = preRoundState;
    }

    public String getSubBytesState() {
        return subBytesState;
    }

    public void setSubBytesState(String subBytesState) {
        this.subBytesState = subBytesState;
    }

    public String getShiftRowsState() {
        return shiftRowsState;
    }

    public void setShiftRowsState(String shiftRowsState) {
        this.shiftRowsState = shiftRowsState;
    }

    public String getMixColumnsState() {
        return mixColumnsState;
    }

    public void setMixColumnsState(String mixColumnsState) {
        this.mixColumnsState = mixColumnsState;
    }

    public String getAddRoundKeyState() {
        return addRoundKeyState;
    }

    public void setAddRoundKeyState(String addRoundKeyState) {
        this.addRoundKeyState = addRoundKeyState;
    }

    public boolean isPreRound() {
        return isPreRound;
    }
}
