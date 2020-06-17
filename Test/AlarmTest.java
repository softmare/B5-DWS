import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {
    AlarmMode alarm = new AlarmMode(new Segment());

    @Test public void increaseOneHour() {
        alarm.initAlarmMode();
        alarm.alarm_index = 0;
        alarm.increaseOneHour();
        assertEquals(1, alarm.alarms[0].hour);
        alarm.alarms[0].hour = 23;
        alarm.increaseOneHour();
        assertEquals(0, alarm.alarms[0].hour);
    }
    @Test
    public void increasefiveMinute() {
        alarm.initAlarmMode();
        alarm.alarm_index = 0;
        alarm.increasefiveMinute(); assertEquals(5, alarm.alarms[0].minute);
        alarm.alarms[0].minute = 55;
        alarm.increasefiveMinute ();
        assertEquals(0, alarm.alarms[0].minute);
    }
    @Test
    public void alarminitTime() {
        alarm.initAlarmMode();
        alarm.alarmInitTime();
        assertEquals(0, alarm.alarms[alarm.alarm_index].hour);
        assertEquals(0, alarm.alarms[alarm.alarm_index].minute);
        assertEquals(0, alarm.alarms[alarm.alarm_index].actived);
    }

    @Test
    public void nextAlarm() {
        alarm.initAlarmMode();
        alarm.nextAlarm();
        assertEquals(1, alarm.alarm_index);
        alarm.nextAlarm();
        assertEquals(2, alarm.alarm_index);
        alarm.nextAlarm();
        assertEquals(3, alarm.alarm_index);
        alarm.nextAlarm();
        assertEquals(0, alarm.alarm_index);
    }
    @Test
    public void decideAlarm() {
        alarm.initAlarmMode();
        alarm.decideAlarm();
        assertEquals(1, alarm.alarms[alarm.alarm_index].actived);
    }
    @Test
    public void deleteAlarm() {
        alarm.initAlarmMode();
        alarm.deleteAlarm();
        assertEquals( -1, alarm.alarms[alarm.alarm_index].actived);
    }

}