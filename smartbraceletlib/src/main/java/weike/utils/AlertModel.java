package weike.utils;


public class AlertModel{

    public boolean status;//false0:关   true1:开
    public int hour;
    public int minute;
    public int repeat;
    public int identifier;
    public String mode;

    public boolean isMondayWarnOn;
    public boolean isTuesdayWarnOn;
    public boolean isWednesdayWarnOn;
    public boolean isThursdayWarnOn;
    public boolean isFridayWarnOn;
    public boolean isSaturdayWarnOn;
    public boolean isSundayWarnOn;

    public boolean isMondayWarnOn() {
        return isMondayWarnOn;
    }

    public void setMondayWarnOn(boolean mondayWarnOn) {
        isMondayWarnOn = mondayWarnOn;
    }

    public boolean isTuesdayWarnOn() {
        return isTuesdayWarnOn;
    }

    public void setTuesdayWarnOn(boolean tuesdayWarnOn) {
        isTuesdayWarnOn = tuesdayWarnOn;
    }

    public boolean isWednesdayWarnOn() {
        return isWednesdayWarnOn;
    }

    public void setWednesdayWarnOn(boolean wednesdayWarnOn) {
        isWednesdayWarnOn = wednesdayWarnOn;
    }

    public boolean isThursdayWarnOn() {
        return isThursdayWarnOn;
    }

    public void setThursdayWarnOn(boolean thursdayWarnOn) {
        isThursdayWarnOn = thursdayWarnOn;
    }

    public boolean isSaturdayWarnOn() {
        return isSaturdayWarnOn;
    }

    public void setSaturdayWarnOn(boolean saturdayWarnOn) {
        isSaturdayWarnOn = saturdayWarnOn;
    }

    public boolean isFridayWarnOn() {
        return isFridayWarnOn;
    }

    public void setFridayWarnOn(boolean fridayWarnOn) {
        isFridayWarnOn = fridayWarnOn;
    }

    public boolean isSundayWarnOn() {
        return isSundayWarnOn;
    }

    public void setSundayWarnOn(boolean sundayWarnOn) {
        isSundayWarnOn = sundayWarnOn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "AlertModel{" +
                "status=" + status +
                ", hour=" + hour +
                ", minute=" + minute +
                ", repeat=" + repeat +
                ", identifier=" + identifier +
                ", mode='" + mode + '\'' +
                ", isMondayWarnOn='" + isMondayWarnOn + '\'' +
                ", isTuesdayWarnOn='" + isTuesdayWarnOn + '\'' +
                ", isWednesdayWarnOn='" + isWednesdayWarnOn + '\'' +
                ", isThursdayWarnOn='" + isThursdayWarnOn + '\'' +
                ", isFridayWarnOn='" + isFridayWarnOn + '\'' +
                ", isSaturdayWarnOn='" + isSaturdayWarnOn + '\'' +
                ", isSundayWarnOn='" + isSundayWarnOn + '\'' +
                '}';
    }
}
