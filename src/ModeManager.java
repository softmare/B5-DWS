public class ModeManager implements Mode{
    //Fields
    public Segment segment;
    public ButtonActionCallback on_button_d_pressed;
    public Mode[] unused_modes = new Mode[2];
    public Mode[] used_modes = new Mode[4];
    public Mode current_mode = used_modes[0];
    public int mode_index = 0;
    public StandardCallback forced_action = null;
    public boolean overwrite_action_exist = false;
    public boolean is_main_screen = true;
    public ModeManagerInteracter instance;



    public ModeManager(Segment segment){
        this.segment = segment;
    }

    public void initWatch(){
        instance = ModeManagerInteracter.getInstance();
        registerCallback();
        used_modes[0] = new CurrentTimeMode(segment);
        used_modes[1] = new WorldTimeMode(segment);
        used_modes[2] = new StopWatchMode(segment);
        used_modes[3] = new TimerMode(segment);
        unused_modes[0] = new ThemeMode(segment);
        unused_modes[1] = new AlarmMode(segment);
        ((CurrentTimeMode)used_modes[0]).InitCurrentTimeMode();
        ((WorldTimeMode)used_modes[1]).initWorldTimeMode();
        ((StopWatchMode)used_modes[2]).initStopWatchMode();
        ((TimerMode)used_modes[3]).initTimerMode();
        ((ThemeMode)unused_modes[0]).initThemeMode();
        ((AlarmMode)unused_modes[1]).initAlarmMode();
        displayCurrentMode();
    }

    public void requestButtonA(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                decreaseModeIndex();
            }else{
                current_mode.OnButtonA();
            }
        }
    }

    public void requestButtonB(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                increaseModeIndex();
            }else{
                current_mode.OnButtonB();
            }
        }
    }

    public void requestButtonC(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                useCurrentMode();
            }else{
                current_mode.OnButtonC();
            }
        }
    }

    public void onButtonD(){
        if(overwrite_action_exist){
            forced_action.CallbackFunction();
        }else{
            if(is_main_screen){
                useConfigMode();
            }else{
                current_mode.OnEndOfThisMode();
                is_main_screen = true;
                displayCurrentMode();
            }
        }
    }

    public void registerCallback(){
        instance.registerCancelForceAction(new StandardCallback() {
            @Override
            public void CallbackFunction() {
                cancelForcedAction();
            }
        });
        instance.registerReserveForcedAction(new MethodCallback() {
            @Override
            public void IvokeMethod(StandardCallback sc) {
                reserveForcedAction(sc);
            }
        });
    }

    public void increaseModeIndex(){
        mode_index ++;
        if(mode_index > 3){mode_index = 0;}
        displayCurrentMode();
    }

    public void decreaseModeIndex(){
        mode_index --;
        if(mode_index < 0){mode_index = 3;}
        displayCurrentMode();
    }

    public void useConfigMode(){
        useThisMode(this);
    }

    public void useCurrentMode(){
        useThisMode(used_modes[mode_index]);
    }

    public void useThisMode(Mode mode){
        current_mode = mode;
        current_mode.OnInitThisMode();
        is_main_screen = false;
    }

    public void displayCurrentMode(){
        String upperContent, lowerContent;
        upperContent = "      " + (mode_index + 1) + "     ";
        lowerContent = used_modes[mode_index].toString();
        segment.setSegmentLower(lowerContent, true);
        segment.setSegmentUpper(upperContent, true);
    }

    public void reserveForcedAction(StandardCallback sc){
        if(sc != null){
            forced_action = sc;
            overwrite_action_exist = true;
        }
    }

    public void cancelForcedAction(){
        forced_action = null;
        overwrite_action_exist = false;
    }

    @Override
    public void OnButtonA() {
        Mode temp = used_modes[mode_index];
        used_modes[mode_index] = unused_modes[0];
        unused_modes[0] = temp;
        current_mode.OnEndOfThisMode();
    }

    @Override
    public void OnButtonB() {
        Mode temp = used_modes[mode_index];
        used_modes[mode_index] = unused_modes[1];
        unused_modes[1] = temp;
        current_mode.OnEndOfThisMode();
    }

    @Override
    public void OnButtonC() {
        // Do Nothing!! -> DummyMethod
    }

    @Override
    public void OnEndOfThisMode() {
        is_main_screen = true;
        displayCurrentMode();
    }

    @Override
    public void OnInitThisMode() {
        String upperContent, lowerContent;
        upperContent = unused_modes[0].toString().substring(0,4) + "    " + unused_modes[1].toString().substring(0,4);
        lowerContent = used_modes[mode_index].toString();
        segment.setSegmentLower(lowerContent, true);
        segment.setSegmentUpper(upperContent, true);
    }



}
