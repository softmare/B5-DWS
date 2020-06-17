import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class StopWatchTest {
    Segment segment = new Segment();

    @Test
    public void startStopWatch() throws InterruptedException {
        StopWatchMode stopWatchMode = new StopWatchMode (segment);
        stopWatchMode.initStopWatchMode(); stopWatchMode.startStopWatch();
        assertTrue (stopWatchMode.is_stop_watch_running);
        assertTrue (stopWatchMode.is_stop_watch_started);
        Thread.sleep( 1910);
        assertEquals( 1, stopWatchMode.elapsed_time.getSeconds());
    }

    @Test public void maxStopWatch() throws InterruptedException {
        StopWatchMode stopWatchMode = new StopWatchMode(segment);
        stopWatchMode.initStopWatchMode();
        stopWatchMode.elapsed_time.setMinute(59);
        stopWatchMode.elapsed_time.setSeconds(58);
        stopWatchMode.startStopWatch();
        Thread.sleep(1050);
        assertTrue(stopWatchMode.testStopWatchMax());
    }

    @Test public void pauseStopwatch() throws InterruptedException {
        StopWatchMode stopWatchMode = new StopWatchMode(segment);
        stopWatchMode.initStopWatchMode();
        stopWatchMode.startStopWatch();
        assertTrue(stopWatchMode.is_stop_watch_running);
        stopWatchMode.pauseStopWatch();
        assertFalse(stopWatchMode.is_stop_watch_running);
    }

    @Test public void continueStopwatch() {
        StopWatchMode stopWatchMode = new StopWatchMode(segment);
        stopWatchMode.initStopWatchMode();
        stopWatchMode.startStopWatch();
        stopWatchMode.pauseStopWatch();
        stopWatchMode.continueStopWatch();
        assertTrue(stopWatchMode.is_stop_watch_running);
    }

    @Test public void resetTimer() throws InterruptedException {
        StopWatchMode stopWatchMode = new StopWatchMode (segment);
        stopWatchMode.initStopWatchMode();
        stopWatchMode.startStopWatch();
        stopWatchMode.pauseStopWatch();
        stopWatchMode.resetStopWatch();
        assertFalse (stopWatchMode.is_stop_watch_running);
        assertEquals( 0, stopWatchMode.elapsed_time.getMinute());
        assertEquals( 0, stopWatchMode.elapsed_time.getSeconds());
    }

    @Test public void isResetRun() {
        StopWatchMode stopWatchMode = new StopWatchMode(segment);
        stopWatchMode.initStopWatchMode();
        stopWatchMode.startStopWatch();
        stopWatchMode.OnButtonC();
    }

}