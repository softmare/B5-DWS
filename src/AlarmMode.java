import java.util.*;


public class AlarmMode implements Mode{

    private TimeManager timeManager;
    private Segment segment;
    private Alarm[] alarms = new Alarm[4];
    private int alarm_index;
    private ButtonActionCallback button_a;
    private ButtonActionCallback button_b;
    private ButtonActionCallback button_c;
    private Thread alarmChecker;
    private Buzzer buzzer;
    private boolean isAlarmSettingMode;
    Time current_time = new Time();
    private int hour,minute;

    public AlarmMode(Segment segment){
        this.segment = segment;
    }


    void initAlarmMode(){
        buzzer = Buzzer.getInstance();
        timeManager = TimeManager.getInstance();
        MappingAlarmMode();
        for(int i=0;i<4;i++){
            alarms[i] = new Alarm(0,0,-1);
        }
        //시간을 0으로 초기화 해주고, activated를 모두 false로 초기화해준다.
        //1분마다 checkringalarmExist 호출
        alarmChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(checkRingAlarmExist()){
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        alarmChecker.start();
    }

    void enableAlarm(){
        //initAlarm에서 생성한 리스트에서 해당 인덱스에 해당하는 값을 불러와
        //actived를 true로 바꿔준다.
        alarms[alarm_index].actived = 1;
        displayCurrentAlarm();
    }

    void disableAlarm(){
        //initAlarm에서 생성한 리스트에서 해당 인덱스에 해당하는 값을 불러와
        //actived를 false로 바꿔준다.
        alarms[alarm_index].actived = 0;
        displayCurrentAlarm();
    }

    boolean checkRingAlarmExist(){
        //activated가 true이고
        //alarmlist의 시간과 currenttime이 같다면
        //onbuzzer를 호출한다.
        current_time = timeManager.getCurrentTime();
        for(int i=0;i<4;i++){
            if(alarms[i].actived == 1){
                if(alarms[i].getHour() == timeManager.getCurrentTime().getHour() && alarms[i].getMinute() == timeManager.getCurrentTime().getMinute()){
                    buzzer.OnBuzzer();
                    return true;
                }
            }
        }
        return false;
    }

    void increasealarm_index(){
        //alarm index를 1증가시켜주는 함수
        alarm_index++;
    }

    void addAlarm(){
        MappingAlarmTimeSettingMode();
    }

    void increaseOneHour(){
        //hour++
        alarms[alarm_index].hour += 1;
        if(alarms[alarm_index].hour > 23){
            alarms[alarm_index].hour = 0;
        }
        displayCurrentAlarm();
    }

    void increasefiveMinute(){
        //fiveMinute++
        alarms[alarm_index].minute += 5;
        if(alarms[alarm_index].minute > 55){
            alarms[alarm_index].minute = 0;
        }
        displayCurrentAlarm();
    }

    void alarmInitTime(){
        //alarmTime -> 00
        //activated = false
        alarms[alarm_index].hour = 0;
        alarms[alarm_index].minute = 0;
        alarms[alarm_index].actived = 0;
    }

    void MappingAlarmTimeSettingMode(){
        button_a = new ButtonActionCallback(){

            @Override
            public void OnButtonPressed() {
                // TODO Auto-generated method stub
                increaseOneHour();
            }
        };
        button_b = new ButtonActionCallback(){

            @Override
            public void OnButtonPressed() {
                // TODO Auto-generated method stub
                increasefiveMinute();
            }
        };
        button_c = new ButtonActionCallback(){

            @Override
            public void OnButtonPressed() {
                // TODO Auto-generated method stub
                decideAlarm();
            }
        };
    }

    void MappingAlarmMode(){
        button_a = new ButtonActionCallback(){
            @Override
            public void OnButtonPressed() {
                // TODO Auto-generated method stub
                if(alarms[alarm_index].getActive() == -1){
                    addAlarm();
                }else{
                    if(alarms[alarm_index].getActive() == 0){
                        enableAlarm();
                    }else{
                        disableAlarm();
                    }
                }
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                nextAlarm();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                deleteAlarm();
            }
        };
    }

    void nextAlarm(){
        alarm_index++;
        if(alarm_index > 3) alarm_index = 0;
        displayCurrentAlarm();
    }

    void decideAlarm() {
        alarms[alarm_index].actived = 1;
        MappingAlarmMode();
        displayCurrentAlarm();
    }

    void deleteAlarm() {
        alarms[alarm_index].actived = -1;
        alarms[alarm_index].hour = 0;
        alarms[alarm_index].minute = 0;
        displayCurrentAlarm();
    }

    @Override
    public void OnButtonA() {
        button_a.OnButtonPressed();
    }

    @Override
    public void OnButtonB() {
        button_b.OnButtonPressed();
    }

    @Override
    public void OnButtonC() {
        button_c.OnButtonPressed();
    }

    @Override
    public void OnEndOfThisMode() {
        // TODO Auto-generated method stub

    }

    @Override
    public void OnInitThisMode() {
        // TODO Auto-generated method stub
        displayCurrentAlarm();
    }


    private void displayCurrentAlarm(){
        int hour, min;
        hour = alarms[alarm_index].hour;
        min = alarms[alarm_index].minute;
        segment.setSegmentUpper((alarm_index + 1) + "    " + alarms[alarm_index].getState() , true);
        segment.setSegmentLower( ((hour < 10)?"0" + hour : hour)+ ":" + ((min < 10)?"0" + min : min), true);
    }

    @Override
    public String toString(){
        return "ALARM";
    }
}