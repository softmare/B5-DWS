import java.awt.*;

public class Segment {
    private Color text_color, background_color;
    private boolean upper_enabled, lower_enabled;
    private String upper_segment,lower_segment;

    public Segment(){
        text_color = Color.WHITE;
        background_color = Color.BLACK;
        upper_segment = null;
        lower_segment = null;
        upper_enabled = true;
        lower_enabled = true;
    }

    public void setSegmentUpper(String content, boolean enable){upper_segment = (upper_enabled = enable)?trimElement(content,12):null;}
    public void setSegmentLower(String content, boolean enable){lower_segment = (lower_enabled = enable)?trimElement(content,8):null;}
    public void setBackgroundColor(Color color){this.background_color = color;}
    public void setTextColor(Color color){this.text_color = color;}
    public Color getTextColor(){return text_color;}
    public Color getBackgroundColor(){return background_color;}
    public String getSegmentUpper(){return upper_enabled?upper_segment:null;}
    public String getSegmentLower(){return lower_enabled?lower_segment:null;}
    public String trimElement(String s, int len){
        if(s.length() == len){
            return s;
        }
        if(s.length() < len){
            boolean dir = true;
            while (s.length() < len){
                dir = !dir;
                if(dir){
                    s = s + " ";
                }else{
                    s = " " + s;
                }
            }
            return s;
        }else{
            return s.substring(0,len);
        }
    }
}
