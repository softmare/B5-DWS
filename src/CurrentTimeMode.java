public class CurrentTimeMode implements Mode{
    private TimeManager timeManager;    // 현재 시간을 계산해주는 모듈을 저장하고 있는 변수.
    private Thread currentTimeUpdater;
    private Segment segment;
    private boolean is_watch_running = false;


    public CurrentTimeMode(Segment segment){
        this.segment = segment;
    }

    public void InitCurrentTimeMode() {
        timeManager = TimeManager.getInstance();
        currentTimeUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if(is_watch_running) {
                            Thread.sleep(1000);
                            if(is_watch_running)
                            SyncWithCurrentTime();
                        }else{
                            Thread.yield();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        currentTimeUpdater.start();
    }

    private void SyncWithCurrentTime() {
        Time curr_time;
        curr_time = timeManager.getCurrentTime();
        segment.setSegmentUpper(curr_time.getYear() +"." + curr_time.getMounth() + "." +curr_time.getDay(), true);
        segment.setSegmentLower(curr_time.getHour()+ ":" + curr_time.getMinute() + ":" + curr_time.getSeconds(), true);
    }

    @Override
    public void OnButtonA() {
        // Dummy Method <- Do Nothing
    }

    @Override
    public void OnButtonB() {
        // Dummy Method <- Do Nothing
    }

    @Override
    public void OnButtonC() {
        // Dummy Method <- Do Nothing
    }

    @Override
    public void OnEndOfThisMode() {
        is_watch_running = false;
    }

    @Override
    public void OnInitThisMode() {
        is_watch_running = true;
    }
}