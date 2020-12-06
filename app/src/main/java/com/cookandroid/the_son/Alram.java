package com.cookandroid.the_son;

public class Alram {
    private int alramOnOff, soundOnOff; //알람을 전체적으로 키고 끄기 + 소리 끄고 키기
    private int BLDcorrect, workcorrect;    // 자세교정 스위치 - 0이면 작동x 1이면 작동o
    private int BLDstretching, workstretching;  //스트레칭 스위치 - 0이면 작동x 1이면 작동o
    private int startHourC, startMinC, endHourC, endMinC;   // 자세교정 설정시간대 시작시간 및 종료 시간
    private int startHourS, startMinS, endHourS, endMinS;   // 스트레칭 설정시간대 시작시간 및 종료 시간

    public Alram(){
        alramOnOff = 0;
        soundOnOff = 0;
        BLDcorrect = 0;
        workcorrect = 0;
        BLDstretching = 0;
        workstretching = 0;
        startHourC = 9;
        startMinC = 0;
        endHourC = 18;
        endMinC = 0;
        startHourS = 9;
        startMinS = 0;
        endHourS = 18;
        endMinS = 0;
    }

    public int getAlramOnOff() {        return alramOnOff;    }
    public int getSoundOnOff() {        return soundOnOff;    }
    public int getBLDcorrect() {        return BLDcorrect;    }
    public int getWorkcorrect() {        return workcorrect;    }
    public int getBLDstretching() {        return BLDstretching;    }
    public int getWorkstretching() {        return workstretching;    }
    public int getStartHourC() {        return startHourC;    }
    public int getStartMinC() {        return startMinC;    }
    public int getEndHourC() {        return endHourC;    }
    public int getEndMinC() {        return endMinC;    }
    public int getStartHourS() {        return startHourS;    }
    public int getStartMinS() {        return startMinS;    }
    public int getEndHourS() {        return endHourS;    }
    public int getEndMinS() {        return endMinS;    }

    public void setAlramOnOff(int alramOnOff) {        this.alramOnOff = alramOnOff;    }
    public void setSoundOnOff(int soundOnOff) {        this.soundOnOff = soundOnOff;    }
    public void setBLDcorrect(int BLDcorrect) {        this.BLDcorrect = BLDcorrect;    }
    public void setWorkcorrect(int workcorrect) {        this.workcorrect = workcorrect;    }
    public void setBLDstretching(int BLDstretching) {        this.BLDstretching = BLDstretching;    }
    public void setWorkstretching(int workstretching) { this.workstretching = workstretching;    }
    public void setStartHourC(int startHour) {        this.startHourC = startHour;    }
    public void setStartMinC(int startMin) {        this.startMinC = startMin;    }
    public void setEndHourC(int endHour) {        this.endHourC = endHour;    }
    public void setEndMinC(int endMin) {        this.endMinC = endMin;    }
    public void setStartHourS(int startHour) {        this.startHourS = startHour;    }
    public void setStartMinS(int startMin) {        this.startMinS = startMin;    }
    public void setEndHourS(int endHour) {        this.endHourS = endHour;    }
    public void setEndMinS(int endMin) {        this.endMinS = endMin;    }

}
