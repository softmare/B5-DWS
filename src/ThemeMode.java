import java.awt.*;

public class ThemeMode implements Mode{
    int theme_index = 0;
    Theme[] themes = new Theme[8];
    Segment segment;

    public ThemeMode(Segment segment){
        this.segment = segment;
    }

    void initThemeMode() {
        themes[0] = new Theme(Color.LIGHT_GRAY,Color.DARK_GRAY,"DEFAULT");
        themes[1] = new Theme(Color.DARK_GRAY,Color.LIGHT_GRAY,"MODERN");
        themes[2] = new Theme(Color.CYAN,Color.DARK_GRAY,"NEON1");
        themes[3] = new Theme(Color.GREEN,Color.DARK_GRAY,"NEON2");
        themes[4] = new Theme(Color.ORANGE,Color.PINK,"SPRING");
        themes[5] = new Theme(Color.WHITE,Color.CYAN,"SUMMER");
        themes[6] = new Theme(Color.ORANGE,Color.MAGENTA,"TROPICAL");
        themes[7] = new Theme(Color.CYAN,Color.BLUE,"DEEP SEA");
        syncUiWithTheme(themes[0]);
    }

    void nextTheme() {
        if(theme_index < themes.length) theme_index++;
        if(theme_index == themes.length) theme_index = 0;
        displayCurrentTheme();
    }

    void prevTheme() {
        if(theme_index > -1 ) theme_index--;
        if(theme_index == -1) theme_index = themes.length - 1;
        displayCurrentTheme();
    }

    void decideTheme() {
        syncUiWithTheme(themes[theme_index]);
    }

    void syncUiWithTheme(Theme theme) {
        segment.setTextColor(theme.getText());
        segment.setBackgroundColor(theme.getBackground());
    }

    @Override
    public void OnButtonA() {
        prevTheme();
    }

    @Override
    public void OnButtonB() {
        nextTheme();
    }

    @Override
    public void OnButtonC() {
        decideTheme();
    }

    @Override
    public void OnEndOfThisMode() {

    }

    @Override
    public void OnInitThisMode() {
        displayCurrentTheme();
    }

    private void displayCurrentTheme(){
        segment.setSegmentUpper("",false);
        segment.setSegmentLower(themes[theme_index].getName(),true);
    }

    @Override
    public String toString(){
        return "THEME";
    }
}