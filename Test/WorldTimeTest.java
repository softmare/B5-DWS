import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTimeTest {
    WorldTimeMode testMode = new WorldTimeMode(new Segment());

    @Test
    public void initWorldTimeMode () {
        testMode.initWorldTimeMode();
        assertEquals(-17, testMode.worlds[0].weight.getHour());
        assertEquals("WASHINGTON", testMode.worlds[0].name);
    }

    @Test
    public void increaseWorldTimeIndex() {
        testMode.world_index = testMode.worlds.length - 1;
        testMode.increaseWorldTimeIndex ();
        assertEquals(0, testMode.world_index);
    }

    @Test
    public void decreaseWorldTimeIndex() {
        testMode.world_index = 0;
        testMode.decreaseWorldTimeIndex();
        assertEquals( testMode.worlds.length - 1, testMode.world_index);
    }

    @Test public void holdCurrentWorldTime() {
        testMode.locked = false;
        testMode.holdCurrentWorldTime();
        assertEquals(true, testMode.locked);
    }

    @Test public void releaseCurrentWorldTimeRock() {
        testMode.locked = true;
        testMode.releaseCurrentWorldTimeRock();
        assertEquals(false, testMode.locked);
    }
}