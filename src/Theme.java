import java.awt.*;

public class Theme {
    public Color text, background;
    public String name;
    public Theme(Color text, Color background, String name){
        this.text = text;
        this.background = background;
        this.name = name;
    }

    public Color getText(){
        return text;
    }

    public Color getBackground(){
        return background;
    }

    public String getName(){
        return name;
    }
}
