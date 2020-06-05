import java.time.LocalDateTime;

public class Time {
    private int seconds, minute, hour;
    private int year, mounth, day;

    public Time(){
        this.seconds = 0;
        this.minute = 0;
        this.hour = 0;
        this.year = 0;
        this.mounth = 0;
        this.day = 0;
    }

    public Time(int seconds, int minute, int hour, int day, int mounth, int year){
        this.seconds = seconds;
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.mounth = mounth;
        this.year = year;
    }

    public Time(LocalDateTime time){
        this.seconds = time.getSecond();
        this.minute = time.getMinute();
        this.hour = time.getHour();
        this.day = time.getDayOfMonth();
        this.mounth = time.getMonthValue();
        this.year = time.getYear();
    }

    public Time(Time time){
        this.seconds = time.seconds;
        this.day = time.day;
        this.hour = time.hour;
        this.minute = time.minute;
        this.year = time.year;
        this.mounth = time.mounth;
    }


    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void addTime(Time target){
        seconds += target.getSeconds();
        minute += target.getMinute();
        hour += target.getHour();
        day += target.getDay();
        mounth += target.getMounth();
        year += target.getYear();
    }

    public String makeSugarStringDay(){
        String temp, buffer;
        buffer = hour + "";
        temp = "";
        if(buffer.length() < 2){
            buffer = "0" + buffer;
        }
        temp += buffer;
        temp += ":";
        buffer = minute + "";
        if(buffer.length() < 2){
            buffer = "0" + buffer;
        }
        temp += buffer;
        temp += ":";
        buffer = seconds + "";
        if(buffer.length() < 2){
            buffer = "0" + buffer;
        }
        temp += buffer;
        return temp;
    }

    public String makeSugarStringYear(){
        String temp, buffer;
        buffer = year + "";
        temp = "";
        while(buffer.length() < 4){
            buffer = "0" + buffer;
        }
        temp += buffer;
        temp += ":";
        buffer = mounth + "";
        if(buffer.length() < 2){
            buffer = "0" + buffer;
        }
        temp += buffer;
        temp += ":";
        buffer = day + "";
        if(buffer.length() < 2){
            buffer = "0" + buffer;
        }
        temp += buffer;
        return temp;
    }

    public static int convertMinuteToSeconds(int minute){
        return minute * 60;
    }

    public static int convertHourToMinute(int hour){
        return hour * 60;
    }

    public static int convertSecondsToMinute(int seconds){
        return seconds / 60;
    }

    public static int convertMinuteToHour(int minute){
        return minute / 60;
    }
}
