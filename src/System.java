import java.awt.*;

public class System {

    private Segment segment;
    private ModeManager mode_manager;


    public System(){
        segment = new Segment();
        mode_manager = new ModeManager(segment);
    }

    public void pressButtonA(){
        mode_manager.requestButtonA();
    }

    public void pressButtonB(){
        mode_manager.requestButtonB();
    }

    public void pressButtonC(){
        mode_manager.requestButtonC();
    }

    public void pressButtonD(){
        mode_manager.onButtonD();
    }

    public String getSegmentContentLower(){
        return segment.getSegmentLower();
    }

    public String getSegmentContentUpper(){
        return segment.getSegmentUpper();
    }

    public Color getTextColor(){
        return segment.getTextColor();
    }

    public Color getBackGroundColor(){
        return segment.getBackgroundColor();
    }

    public void initWatch(){
        mode_manager.initWatch();
    }
}
