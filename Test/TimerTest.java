import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerTest {
    TimerMode timerMode = new TimerMode(new Segment());

    public TimerTest(){
        timerMode.initTimerMode();
    }

    @Test
    public void checkZeroNotStart() {
        timerMode.startTimer();
        assertTrue (timerMode.checkTimerZero());
        assertFalse(timerMode.is_timer_running);
    }

    @Test
    public void startTimer() throws InterruptedException {
        for(int i = 0; i < 3; i++) {
            timerMode.increaseTimerSeconds();
        }
        timerMode.startTimer();
        assertTrue(timerMode.is_timer_running);
        assertTrue(timerMode.is_timer_started);
        Thread.sleep(1010);
        assertEquals(2, timerMode.total_seconds);
    }
    @Test
    public void increaseTimerMinute() {
        timerMode.increaseTimerMinute();
        assertEquals( 1, timerMode.setted_time.getMinute());
    }

    @Test
    public void maxIncreaseMinute() {
        for(int i = 0; i < 59; i++) {
            timerMode.increaseTimerMinute();
        }
        assertEquals( 59, timerMode.setted_time.getMinute());
        timerMode.increaseTimerMinute();
        assertEquals( 0, timerMode.setted_time.getMinute());
    }

    @Test
    public void increaseTimerSeconds() {
        timerMode.increaseTimerSeconds();
        assertEquals( 1, timerMode.setted_time.getSeconds());
    }
    @Test
    public void maxIncreaseSeconds() {
        for(int i = 0; i < 59; i++) {
            timerMode.increaseTimerSeconds();
        }
        assertEquals( 59, timerMode.setted_time.getSeconds());
        timerMode.increaseTimerSeconds();
        assertEquals( 0, timerMode.setted_time.getSeconds());
    }
    @Test
    public void pauseTimer() {
        timerMode.increaseTimerSeconds();
        timerMode.increaseTimerSeconds(); timerMode.startTimer();
        timerMode.pauseTimer();
        assertFalse(timerMode.is_timer_running);
    }
    @Test
    public void continueTimer() {
        timerMode.increaseTimerSeconds();
        timerMode.increaseTimerSeconds();
        timerMode.startTimer();
        timerMode.pauseTimer();
        timerMode.continueTimer();
        assertTrue(timerMode.is_timer_running);
    }

    @Test
    public void cancelTimer() {
        timerMode.increaseTimerSeconds();
        timerMode.increaseTimerSeconds();
        timerMode.startTimer();
        assertTrue(timerMode.is_timer_running); timerMode.cancelTimer();
        assertFalse(timerMode.is_timer_running);
    }
}