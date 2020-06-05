public class Alarm{
    int hour;
    int minute;
    int actived; // -1: none, 0: disable, 1:ensable
    private String[] state = {"NONE","DISABLE","enable"};
    Time ringTime;

    public String getState(){
        return state[actived + 1];
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public int getHour(){
        return hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public int getMinute(){
        return minute;
    }

    public void setActive(int active){
        this.actived = active;
    }

    public int getActive(){
        return actived;
    }

    public Alarm(int hour, int minute, int actived){
        this.hour = hour;
        this.minute = minute;
        this.actived = actived;
    }

}