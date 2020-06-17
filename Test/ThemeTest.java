import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class ThemeTest {
    ThemeMode testTheme = new ThemeMode(new Segment());

    @Test
    public void initThemeMode() {
        testTheme.initThemeMode();
        assertEquals(Color.WHITE, testTheme. themes[0].getText());
        assertEquals(Color.BLACK, testTheme. themes[0].getBackground());
        assertEquals("WINTER1", testTheme. themes[0].getName());
    }

    @Test public void nextTheme () {
        testTheme.initThemeMode();
        testTheme.theme_index = 0;
        testTheme.nextTheme();
        assertEquals(1, testTheme.theme_index);
        testTheme.theme_index = testTheme.themes.length - 1;
        testTheme.nextTheme();
        assertEquals(0, testTheme.theme_index);
    }
    @Test public void prevTheme() {
        testTheme.initThemeMode();
        testTheme.theme_index = 1;
        testTheme.prevTheme();
        assertEquals( 0, testTheme.theme_index);
        testTheme.theme_index = 0;
        testTheme.prevTheme();
        assertEquals( testTheme. themes.length-1, testTheme.theme_index);
    }
}