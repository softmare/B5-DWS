import java.awt.*;

public class ThemeMode implements Mode{
    public int theme_index = 0;
    public Theme[] themes = new Theme[8];
    public Segment segment;

    public ThemeMode(Segment segment){
        this.segment = segment;
    }

    public void initThemeMode() {
        themes[0] = new Theme(Color.WHITE,Color.BLACK,"WINTER1");
        themes[1] = new Theme(new Color(0,0,0),new Color(195,195,195),"WINTER2");
        themes[2] = new Theme(new Color(255,255,255),new Color(255,184,36),"SPRING1");
        themes[3] = new Theme(new Color(109,109,109),new Color(255,164,209),"SPRING2");
        themes[4] = new Theme(new Color(18,95,41),new Color(143,188,75),"SUMMER1");
        themes[5] = new Theme(new Color(63,72,204),new Color(130,192,255),"SUMMER2");
        themes[6] = new Theme(new Color(18,74,0),new Color(255,136,57),"AUTUMN1");
        themes[7] = new Theme(new Color(255,255,255),new Color(128,64,0),"AUTUMN2");
        syncUiWithTheme(themes[0]);
    }

    public void nextTheme() {
        if(theme_index < themes.length) theme_index++;
        if(theme_index == themes.length) theme_index = 0;
        displayCurrentTheme();
    }

    public void prevTheme() {
        if(theme_index > -1 ) theme_index--;
        if(theme_index == -1) theme_index = themes.length - 1;
        displayCurrentTheme();
    }

    public void decideTheme() {
        syncUiWithTheme(themes[theme_index]);
    }

    public void syncUiWithTheme(Theme theme) {
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

    public void displayCurrentTheme(){
        segment.setSegmentUpper("",false);
        segment.setSegmentLower(themes[theme_index].getName(),true);
    }

    @Override
    public String toString(){
        return "THEME";
    }
}