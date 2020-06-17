import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemTest {
    @Test
    public void initWatchTest(){
        Segment segment = new Segment();
        ModeManager mm = new ModeManager(segment);
        mm.initWatch(); assertNotNull(mm. used_modes[0]);
        assertNotNull(mm. used_modes[1]);
        assertNotNull(mm.used_modes[2]);
        assertNotNull(mm. used_modes[3]);
        assertNotNull(mm. unused_modes[0]);
        assertNotNull(mm. unused_modes[1]);
    }
    @Test
    public void modeConfigTest() {
            Segment segment = new Segment();
            ModeManager mm = new ModeManager(segment);
            mm.initWatch();
            Mode comp = mm.unused_modes[0];
            mm.useConfigMode();
            mm.requestButtonA();
            assertEquals(comp,mm.used_modes[mm.mode_index]);
            comp = mm.unused_modes[1];
            mm.useConfigMode();
            mm.requestButtonB();
            assertEquals(comp, mm.used_modes[mm.mode_index]);
    }

    @Test
    public void indexIncreaseTest() {
        Segment segment = new Segment();
        ModeManager mm = new ModeManager(segment);
        mm.initWatch();
        mm.mode_index = 0;
        for (int i = 0; i < 4; i++) {
            mm.increaseModeIndex();
        }
        assertEquals(0, mm.mode_index);
    }
    @Test
    public void indexDecreaseTest(){
            Segment segment = new Segment();
            ModeManager mm = new ModeManager(segment);
            mm.initWatch();
            mm.mode_index = 0;
            for(int i = 0; i < 4; i ++) {
                mm.decreaseModeIndex();
            }
            assertEquals(0, mm.mode_index);
    }

    @Test
    public void useCurrentModeTest(){
        Segment segment = new Segment();
        ModeManager mm = new ModeManager (segment);
        mm.initWatch();
        Mode current = mm.used_modes[mm.mode_index];
        mm. useCurrentMode();
        assertEquals(current,mm.current_mode);
    }
    @Test
    public void backToMainTest(){
            Segment segment = new Segment();
            ModeManager mm = new ModeManager (segment);
            mm.initWatch();
            mm.useCurrentMode();
            mm.onButtonD();
            assertEquals( true,mm.is_main_screen);
    }

    @Test public void buzzerMultifulRingTest()
    {
        Buzzer buzzer = new Buzzer();
        buzzer.OnBuzzer();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        buzzer.OnBuzzer();
        try {
            Thread.sleep(15000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(true, buzzer.is_buzzer_running);
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(false, buzzer.is_buzzer_running);
    }

    @Test
    public void segmentLowerTest(){
        Segment segment = new Segment();
        segment.setSegmentLower( "short", true);
        assertEquals(  8, segment.getSegmentLower().length());
        segment.setSegmentLower(  "L000000000ong",  true);
        assertEquals(  8, segment.getSegmentLower().length());
    }
    @Test
    public void segmentUpperTest(){
            Segment segment = new Segment();
            segment.setSegmentUpper(  "short",  true);
            assertEquals( 12, segment.getSegmentUpper().length());
            segment.setSegmentUpper(  "L0000000000000000000000ng",  true);
            assertEquals(12, segment.getSegmentUpper().length());
    }

}
