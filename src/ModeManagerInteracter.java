public class ModeManagerInteracter {
    public static ModeManagerInteracter instance = null;

    public MethodCallback forced_action = null;
    public StandardCallback cancel_forced = null;


    public ModeManagerInteracter(){}

    public static ModeManagerInteracter getInstance(){
        if(instance == null){
            instance = new ModeManagerInteracter();
        }
        return instance;
    }


    public void registerReserveForcedAction(MethodCallback callback){
        this.forced_action = callback;
    }

    public void registerCancelForceAction(StandardCallback callback){
        this.cancel_forced = callback;
    }


    public void activeReserveForcedAction(StandardCallback callback){
        if(forced_action != null)
        forced_action.IvokeMethod(callback);
    }

    public void activeCancelForceAction(){
        if(cancel_forced != null)
        cancel_forced.CallbackFunction();
    }
}
